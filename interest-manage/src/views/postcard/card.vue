<style type="text/css">
  .text-style {
    word-wrap:break-word;
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
                <Col span="8">兴趣：
                    <Select v-model="interestid" clearable style="width: 200px">
                        <Option v-for="item in interestList" :value="item.id" :key="item.id">{{ item.title }}</Option>
                    </Select>
                </Col>
                <Col span="8"><Button type="primary" shape="circle" icon="ios-search" @click="search()">搜索</Button></Col>
            </Row>
        </div>
        <div>
            <ul>
            	<li>
                    <Button type="error" icon="md-trash" @click="del()">删除</Button>
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
        <Modal :mask-closable="false" :visible.sync="modal" v-model="modal" width="600" title="查看">
	        <Form :label-width="80" >
	        	<Form-item label="标题:">
	        		<strong class="text-style">{{postcard.title}}</strong>
            </Form-item>
            <Form-item label="内容:">
            	<span class="text-style">{{postcard.content}}</span>
            </Form-item>
            </Form>
	        <div slot="footer">
	            <Button type="error" size="large"  @click="cancel">关闭</Button>
	        </div>
	    </Modal>	
    </div>
</template>
<script>
export default {
  data() {
    return {
      groupId: [],
      interestid: null,
      interestList: [],
      /*修改modal的显示参数*/
      modal: false,
      /*分页total属性绑定值*/
      total: 0,
      /*pageInfo实体*/
      pageInfo: {
        page: 0,
        pageSize: 10
      },
      /*user实体*/
      postcard: {
        id: null,
        username: null,
        title: null,
        interestid: null,
        content: null,
        createtime: null,
        replytime: null
      },
      /*表显示字段*/
      columns1: [
        {
          type: "selection",
          width: 60,
          align: "center"
        },
        {
          title: "用户ID",
          key: "userId"
        },
        {
          title: "兴趣归属",
          render: (h, params) => {
            let interestTitle = '';
            this.interestList.forEach(e=>{
              if (params.row.interestId == e.id) {
                console.log(e.title);
                interestTitle = e.title;
                return;
              }
            });
            return h("div", [
                h("strong", null, interestTitle)
              ]);
          }
        },
        {
          title: "标题",
          width: 500,
          key: "title"
        },
        {
          title: "发帖时间",
          width: 130,
          key: "createTime"
        },
        {
          title: "最近回帖时间",
          width: 130,
          render: (h,params) => {
            let time = this.dateGet(params.row.replyTime);
            return h("span",null,time);
          }
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
                    "/page/card/" +
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
      data1: []
    };
  },
  mounted() {
    /*页面初始化调用方法*/
    this.axios({
      method: "get",
      url: "/interest/bbs/admin/interests/titles"
    })
      .then(
        function(response) {
          this.interestList = response.data.data;
          this.getTable({
            pageInfo: this.pageInfo,
            interestid: this.interestid
          });
        }.bind(this)
      )
      .catch(
        function(error) {
          alter(error);
        }.bind(this)
      );
  },
  methods: {
    /*pageInfo实体初始化*/
    initPageInfo() {
      this.pageInfo.page = 0;
      this.pageInfo.pageSize = 10;
    },
    postcardSet(e) {
      this.postcard.id = e.id;
      this.postcard.username = e.username;
      this.postcard.title = e.title;
      this.postcard.interestid = e.interestid;
      this.postcard.content = e.content;
      this.postcard.createtime = e.createtime;
      this.postcard.replytime = e.replytime;
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
      this.axios({
        method: "get",
        url: "/interest/bbs/admin/postcards",
        params: {
          page: e.pageInfo.page,
          pageSize: e.pageInfo.pageSize,
          interestId: e.interestid
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
          alert(error);
        });
    },
    search() {
      this.initPageInfo();
      this.getTable({
        pageInfo: this.pageInfo,
        interestid: this.interestid
      });
    },
    /*分页点击事件*/
    pageSearch(e) {
      this.pageInfo.page = e - 1;
      this.getTable({
        pageInfo: this.pageInfo,
        interestid: this.interestid
      });
    },
    /*modal的cancel点击事件*/
    cancel() {
      this.modal = false;
    },
    /*表格中双击事件*/
    dblclick(e) {
      this.postcardSet(e);
      this.modal = true;
    },
    postcardInfo(e) {
      console.log(e);
      this.postcardSet(e);
      this.modal = true;
    },
    del() {
      if (this.groupId != null && this.groupId != "") {
        this.axios({
          method: "delete",
          url: "/interest/bbs/postcards",
          data: this.groupId
        })
          .then(
            function(response) {
              this.getTable({
                pageInfo: this.pageInfo,
                interestid: this.interestid
              });
              this.groupId = [];
              this.$Message.info("删除成功");
            }.bind(this)
          )
          .catch(function(error) {
            alert(error);
          });
      }
    },
    change(e) {
      this.setGroupId(e);
    },
    setGroupId(e) {
      this.groupId = [];
      for (var i = 0; i <= e.length - 1; i++) {
        this.groupId.push(e[i].id);
      }
    }
  }
};
</script>
