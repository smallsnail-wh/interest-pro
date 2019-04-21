<style scoped>
  .operation-button{
    margin-right: 3px;
  }
  .del-select {
    margin-bottom: 25px;
  }
  .del-select span{
    margin-right: 5px;
  }
  .content-layout {
    margin: 40px;
  }
  ul {
    list-style: none;
  }
</style>
<template>
	<div class="content-layout">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="6">用户名：
                	<Input v-model="name" placeholder="请输入..." style="width:200px" />
                </Col>
                <Col span="6">用户ID：
                  <Input v-model="userId" placeholder="请输入..." style="width:200px" />
                </Col>
                <Col span="3"><Button type="primary" shape="circle" icon="ios-search" @click="search()">搜索</Button></Col>
            </Row>
        </div>     
        <div class="del-select">
          <span>删除：</span>
          <i-switch size="large" v-model="del" @on-change="switchChange()">
            <span slot="open">开启</span>
            <span slot="close">关闭</span>
          </i-switch>
        </div>       
        <div>
            <ul>
                <li>
                    <Button v-if="del" class="operation-button" type="success" icon="md-build" @click="recover()">恢复</Button>
                    <Button v-if="!del" type="error" icon="md-trash" @click="deleteUser()">删除</Button>
                </li>
                <li>
                    <div style="padding: 10px 0;">
                    	<Table border :columns="columns1" :data="data1" :height="520" @on-selection-change="s=>{change(s)}" @on-row-dblclick="s=>{dblclick(s)}"></Table>
                    </div> 
                </li>
                <li>
                    <div style="text-align: right;">
                        <Page :total="total" :page-size="pageInfo.pageSize" show-elevator show-total @on-change="e=>{pageSearch(e)}"></Page>
                    </div>  
                </li>
            </ul>
        </div>
    </div>
</template>
<script>
export default {
  data() {
    return {
      del: false,
      userId: null,
      /*用于查找的登录名*/
      name: null,
      /*选择的数量*/
      count: null,
      /*选中的组数据*/
      groupId: null,
      /*分页total属性绑定值*/
      total: 0,
      /*pageInfo实体*/
      pageInfo: {
        page: 0,
        pageSize: 10
      },
      /*user实体*/
      user: {
        id: null,
        name: null,
        loginName: null,
        password: null,
        passwordAgain: null,
        email: null
      },
      /*表显示字段*/
      columns1: [
        {
          type: "selection",
          width: 60,
          align: "center"
        },
        {
          title: "ID",
          width: 80,
          key: "id"
        },
        {
          title: "用户名",
          key: "name"
        },
        {
          title: "头像",
          key: "headimg",
          width: 80,
          align: "center",
          render: (h, params) => {
            return h(
              "img",
              {
                attrs: {
                  src: params.row.headimg
                },
                style: {
                  width: '30px',  
                  height: '30px'
                }
              }
            );
          }
        },
        {
          title: "url",
          key: "url",
          width: 300,
          render: (h, params) => {
            return h(
              "a",
              {
                attrs: {
                  href: params.row.url,
                  target: "_blank"
                }
              },
              params.row.url
            );
          }
        },
        {
          title: "邮箱",
          key: "email"
        },
        {
          title: "注册时间",
          key: "createTime"
        },
        {
          title: "操作",
          align: "center",
          key: "action",
          render: (h, params) => {
            return h(
              "a",
              {
                attrs: {
                  href:
                    this.$store.state.domainName +
                    "/user/" +
                    params.row.id,
                  target: "_blank"
                }
              },
              [
                h(
                  "Button",
                  {
                    props: {
                      type: "info"
                    }
                  },
                  "查看"
                )
              ]
            );
          }
        }
      ],
      /*表数据*/
      data1: [],
    };
  },
  mounted() {
    /*页面初始化调用方法*/
    this.getTable({
      pageInfo: this.pageInfo
    });
  },
  methods: {
    /*pageInfo实体初始化*/
    initPageInfo() {
      this.pageInfo.page = 0;
      this.pageInfo.pageSize = 10;
    },
    /*user实体初始化*/
    initUser() {
      this.user.id = null;
      this.user.name = null;
      this.user.loginName = null;
      this.user.password = null;
      this.user.email = null;
    },
    /*userModify实体初始化*/
    initUserModify() {
      this.userModify.id = null;
      this.userModify.name = null;
      this.userModify.loginName = null;
      this.userModify.password = null;
      this.userModify.email = null;
    },
    /*user设置*/
    userSet(e) {
      this.user.id = e.id;
      this.user.name = e.name;
      this.user.loginName = e.loginName;
      this.user.password = e.password;
      this.user.email = e.email;
    },
    /*userModify设置*/
    userModifySet(e) {
      this.userModify.id = e.id;
      this.userModify.name = e.name;
      this.userModify.usertype = e.usertype;
    },
    dateGet(e) {
      var time = new Date(parseInt(e));
      return (
        time.getFullYear() +
        "-" +
        (time.getMonth() + 1) +
        "-" +
        time.getDate() +
        " " +
        time.getHours() +
        ":" +
        time.getMinutes()
      );
    },
    listDateSet(e) {
      for (var i = e.length - 1; i >= 0; i--) {
        e[i].createTime = this.dateGet(e[i].createTime);
      }
    },
    /*得到表数据*/
    getTable(e) {
      let status = 0;
      if(this.del){
        status = 1;
      }
      this.axios({
        method: "get",
        url: "/interest/user/admin/users",
        params: {
          page: e.pageInfo.page,
          pageSize: e.pageInfo.pageSize,
          name: e.name,
          userId: e.userId,
          status: status,
          type: 0
        }
      })
        .then(
          function(response) {
            this.data1 = response.data.data.data;
            this.listDateSet(this.data1);
            this.total = response.data.data.totalCount;
          }.bind(this)
        )
        .catch(function(error) {
          console.log(error);
        });
    },
    /*搜索按钮点击事件*/
    search() {
      this.initPageInfo();
      this.getTable({
        pageInfo: this.pageInfo,
        name: this.name,
        userId: this.userId
      });
    },
    /*分页点击事件*/
    pageSearch(e) {
      this.pageInfo.page = e - 1;
      this.getTable({
        pageInfo: this.pageInfo,
        name: this.name,
        userId: this.userId
      });
    },
    /*table选择后触发事件*/
    change(e) {
      this.setGroupId(e);
    },
    /*通过选中的行设置groupId的值*/
    setGroupId(e) {
      this.groupId = [];
      this.count = e.length;
      for (var i = 0; i <= e.length - 1; i++) {
        this.groupId.push(e[i].id);
      }
    },
    /*删除table中选中的数据*/
    deleteUser() {
      console.log(this.groupId);
      if (this.groupId != null && this.groupId != "") {
        this.axios({
          method: "delete",
          url: "/interest/user/admin/users",
          data: this.groupId
        })
          .then(
            function(response) {
              this.getTable({
                pageInfo: this.pageInfo,
                name: this.name
              });
              this.groupId = null;
              this.count = 0;
              this.$Message.info("删除成功");
            }.bind(this)
          )
          .catch(function(error) {
            alert(error);
          });
      }
    },
    /*恢复table中选中的数据*/
    recover() {
      if (this.groupId != null && this.groupId != "") {
        this.axios({
          method: "patch",
          url: "/interest/user/admin/users",
          data: this.groupId
        })
          .then(
            function(response) {
              this.getTable({
                pageInfo: this.pageInfo,
                name: this.name
              });
              this.groupId = null;
              this.count = 0;
              this.$Message.info("删除成功");
            }.bind(this)
          )
          .catch(function(error) {
            alert(error);
          });
      }
    },
    switchChange(){
      this.groupId = [];
      this.getTable({
        pageInfo: this.pageInfo,
        name: this.name,
        userId: this.userId
      });
    }
  }
};
</script>
