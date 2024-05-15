<template>
  <v-scale-screen  width="1920"
                   height="1080"
                   :delay="500"
                   :fullScreen="false"
                   :boxStyle="{
      background: '#fff',
      overflow: isScale ? 'hidden' : 'auto',
    }"
                   :wrapperStyle="wrapperStyle"
                   :autoScale="isScale"
  >
    <div class="wrapper">
      <!-- 菜单部分 -->
      <Footer></Footer>

      <!-- 使用v-for循环显示标题和对应的dropArea -->
      <div class="dropContainer">
        <div v-for="(dropArea, index) in dropAreas" :key="index" :ref="'dropArea_' + index" class="dropAreaContainer">
          <div class="title">{{ dropArea.title }}</div>
          <div class="dropArea" style="position: relative;">
            <button @click="selectFile(index)"
                    style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; background: transparent; border: none;"></button>
            <svg t="1709129721238" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
                 p-id="2313" width="60" height="60">
              <path
                d="M913.066667 947.2H110.933333a34.133333 34.133333 0 0 1-34.133333-34.133333V110.933333a34.133333 34.133333 0 0 1 34.133333-34.133333h802.133334a34.133333 34.133333 0 0 1 34.133333 34.133333v802.133334a34.133333 34.133333 0 0 1-34.133333 34.133333z m-768-68.266667h733.866666V145.066667H145.066667z"
                p-id="2314" fill="#51a4db"></path>
              <path
                d="M297.813333 578.133333a34.133333 34.133333 0 0 1-24.149333-58.282666l214.186667-214.101334a34.133333 34.133333 0 0 1 48.298666 48.213334l-214.186666 214.186666a34.133333 34.133333 0 0 1-24.149334 9.984z"
                p-id="2315" fill="#1296db"></path>
              <path
                d="M512 818.602667a34.133333 34.133333 0 0 1-34.133333-34.133334V329.813333a34.133333 34.133333 0 0 1 58.282666-24.064l214.186667 214.101334a34.133333 34.133333 0 0 1-48.298667 48.298666L546.133333 412.245333v372.224a34.133333 34.133333 0 0 1-34.133333 34.133334zM756.48 273.664h-488.96a34.133333 34.133333 0 0 1 0-68.266667h488.96a34.133333 34.133333 0 0 1 0 68.266667z"
                p-id="2316" fill="#1296db"></path>
            </svg>
            点击此区域上传文件
            <input type="file" class="fileInput" style="display: none;" @change="handleFiles($event)">
            <!-- 文件名显示在右下角 -->
            <div class="fileName" v-if="dropArea.fileName">{{ dropArea.fileName }}</div>
            <!-- 下载按钮显示在右下角 -->
            <button class="downloadButton" @click="downloadFile(dropArea.id)" v-if="dropArea.fileName">下载</button>
          </div>
        </div>
        <div class="UpdateDiv">
          <div class="UpdateTitle">物流更新(柜号)</div>
          <div class="UpdateBody">
            <p>柜号：</p>
            <input type="text" v-model="cabinetNumber" placeholder="柜号">

            <div class="switcher">
              <select id="status-switcher" v-model="selectedStatus">
                <option value="">所有状态</option>
                <!-- <option value="已装柜">已装柜</option> -->
                <option value="已到港">已到港</option>
                <option value="已到站">已到站</option>
                <option value="清关中">清关中</option>
                <option value="已装车">已装车</option>
                <option value="已到仓">已到仓</option>
                <option value="已完成">已完成</option>
              </select>
            </div>
            <button class="UpdateSubmit" @click="updateStatus"> 更新</button>
          </div>
        </div>
      </div>

      <!-- 文件模板部分 -->
      <div class="file-download">
        <p>文件模板</p>
        <div>
          <!-- 使用v-for循环显示文件下载链接 -->
          <div class="file_d" @click="downloadExcel() "> 点击下载</div>
        </div>
      </div>

      <!-- 文件列表展示 -->
      <div v-for="file in files" :key="file.name" class="download-button">
        <p>本次上传文件名：{{ file.name }}，大小：{{ file.size }}字节</p>
        <button @click="downloadFile(file)">下载</button>
        <button @click="uploadFile(file)" :disabled="isUploadButtonDisabled(file)">
          {{ file.uploading ? '上传中' : '上传' }}
        </button>
        <button @click="deleteFile(file)">删除</button>

      </div>
    </div>
    <!-- 加载动画 -->
    <div class="box" v-if="loadFlag===true"><span></span></div>

  </v-scale-screen>
</template>

<script>
import Footer from '../components/Footer.vue';
import XLSX from 'xlsx';
import axios from 'axios';

export default {
  name: 'OpHome',
  components: {
    Footer // 将组件引入放在components选项下
  },
  data() {
    return {
      dropAreas: [
        {id: 1, title: '单号录入'},
        {id: 2, title: '包裹入柜'}
      ],
      dropIndex: null,
      files: [], // 存储上传的文件信息
      dropzoneActive: false,
      dropAreaIndex: '',
      routerProp: {}, // 确保传递了正确的 router 对象
      routeProp: {}, // 确保传递了正确的 route 对象
      currentUserType: '',
      currentUserName: '',
      list_P: [],
      list_R: [],
      resultFlag: true,
      uploadButtonDisabled: false, // 控制上传按钮是否可点击
      cabinetNumber: '',
      selectedStatus: '',
      list_C: [],
      KeyWord: '',
      loadFlag: false,
      insertFlag: false,
      insertFlag_R: false
    };
  },
  mounted() {
    // console.log("props UT:", this.$route.query.currentUserType);
    // console.log("props UN:", this.$route.query.currentUserName);
    // 页面加载时从localStorage读取用户类型
    if (this.$route.query.currentUserType && this.$route.query.currentUserName) {


    } else {
      this.$router.push('/');
      return;
    }

  },
  methods: {
    updateStatus() {
      this.loadFlag = true;
      if (this.cabinetNumber && this.selectedStatus) {

        if (confirm(`是否将当前柜号(${this.cabinetNumber})的状态更新为${this.selectedStatus}？`)) {
          const data3 = {};
          const data4 = {};
          data3['counter_number'] = this.cabinetNumber;
          data3['state'] = this.selectedStatus;
          data4['counter_number'] = this.cabinetNumber;
          data4['update_state'] = this.selectedStatus;
          // console.log("this.cabinetNumber");
          // console.log(this.cabinetNumber);
          // console.log("this.selectedStatus");
          // console.log(this.selectedStatus);

          // 添加当前操作时间的年、月、日、时、分、秒
          const currentTime = new Date();
          data3['op_time'] = currentTime.getFullYear() + '-' +
            ('0' + (currentTime.getMonth() + 1)).slice(-2) + '-' +
            ('0' + currentTime.getDate()).slice(-2) + ' ' +
            ('0' + currentTime.getHours()).slice(-2) + ':' +
            ('0' + currentTime.getMinutes()).slice(-2) + ':' +
            ('0' + currentTime.getSeconds()).slice(-2);
          data4['time_operating'] = currentTime.getFullYear() + '-' +
            ('0' + (currentTime.getMonth() + 1)).slice(-2) + '-' +
            ('0' + currentTime.getDate()).slice(-2) + ' ' +
            ('0' + currentTime.getHours()).slice(-2) + ':' +
            ('0' + currentTime.getMinutes()).slice(-2) + ':' +
            ('0' + currentTime.getSeconds()).slice(-2);
          data3['manager'] = this.$route.query.currentUserName;
          data4['manager'] = this.$route.query.currentUserName;


          data3['counter_number'] = data3['counter_number'].replace(/ /g, '');


          // 发送数据到后端
          axios.put('https://www.hxlogistics.top/to_url/Product/update/', data3)
            .then((res) => {
              console.log("柜号res");
              console.log(res);
              // if(res.data==="Insert all product!"){
              this.insertFlag = true;
              console.log("柜号this.insertFlag");
              console.log(this.insertFlag);
              // }
              let it;
              let list = [];


              for (let i = 0; i < res.data.length; i++) {
                const data4 = {};
                data4['counter_number'] = this.cabinetNumber;
                data4['update_state'] = this.selectedStatus;
                data4['time_operating'] = currentTime.getFullYear() + '-' +
                  ('0' + (currentTime.getMonth() + 1)).slice(-2) + '-' +
                  ('0' + currentTime.getDate()).slice(-2) + ' ' +
                  ('0' + currentTime.getHours()).slice(-2) + ':' +
                  ('0' + currentTime.getMinutes()).slice(-2) + ':' +
                  ('0' + currentTime.getSeconds()).slice(-2);
                data4['manager'] = this.$route.query.currentUserName;
                data4['tracking_number'] = res.data[i].tracking_number;
                data4['counter_number'] = data4['counter_number'].replace(/ /g, '');
                data4['tracking_number'] = data4['tracking_number'].replace(/ /g, '');

                // console.log(data4);
                list.push(data4);

              }


              axios.post('https://www.hxlogistics.top/to_url/Record/insert/', list)
                .then((res) => {
                  if (res.data === "Insert all record!") {
                    this.insertFlag_R = true;
                    console.log("柜号this.insertFlag_R");
                    console.log(this.insertFlag);
                  }
                  if (this.insertFlag_R === true && this.insertFlag === true) {
                    this.$message({
                      message: "更新成功",
                      type: 'success'
                    });
                    console.log(res.data);
                  } else {
                    this.$message({
                      message: "更新失败",
                      type: 'error'
                    });
                    console.log(res.data);
                  }

                  this.loadFlag = false;
                })
                .catch((err) => {
                  if (err.response && err.response.status === 401) {
                    this.$message({
                      message: "更新错误",
                      type: 'error'
                    });
                    this.loadFlag = false;
                  } else {
                    this.$message({
                      message: "网络错误",
                      type: 'error'
                    });
                    this.loadFlag = false;
                  }
                });


            })
            .catch((err) => {
              if (err.response && err.response.status === 401) {
                this.$message({
                  message: "更新错误",
                  type: 'error'
                });
                this.loadFlag = false;
              } else {
                this.$message({
                  message: "网络错误",
                  type: 'error'
                });
                this.loadFlag = false;
              }
            });


        } else {
          this.loadFlag = false;
          alert('请填写柜号并选择状态');
        }
      }
    },
    getUserRole() {
      // 获取用户角色的逻辑
      // 这里只是一个示例，具体实现取决于你的应用
      return this.currentUserType;
    },
    checkUserType() {
      const userType = getUserType();
      // 使用 userType 进行逻辑处理
    },
    triggerFileInput(dropAreaIndex) {
      this.$nextTick(() => {
        const dropAreaRef = this.$refs['dropArea_' + dropAreaIndex];
        if (!dropAreaRef) {
          console.error('Drop area ref not found!');
          return;
        }

        const dropArea = dropAreaRef[0]; // Assuming that each dropArea ref is an array
        if (!dropArea) {
          console.error('Drop area not found!');
          return;
        }

        const fileInput = dropArea.querySelector('.fileInput');
        if (!fileInput) {
          console.error('File input element not found!');
          return;
        }

        fileInput.click();
      });
    },

    dragEnter(event) {
      event.preventDefault();
      this.dropzoneActive = true;
    },
    dragLeave(event) {
      event.preventDefault();
      this.dropzoneActive = false;
    },
    async cleanString(w) {
      // console.log("w:");
      // console.log(w);
      w = w.replace(/ /g, '');
      // console.log(w);
      return w;
    },
    dragOver(event) {
      event.preventDefault();
    },
    dropFile(operationType) {
      return function (event) {
        event.preventDefault();
        this.dropzoneActive = false;
        const files = event.dataTransfer.files;
        this.handleFiles(files, operationType);
      };
    },
    selectFile(dropAreaIndex) {
      const dropAreaRef = this.$refs['dropArea_' + dropAreaIndex];

      this.dropIndex = dropAreaIndex;
      if (!dropAreaRef) {
        console.error('Drop area ref not found!');
        return;
      }

      const dropArea = dropAreaRef[0]; // Assuming that each dropArea ref is an array
      if (!dropArea) {
        console.error('Drop area not found!');
        return;
      }

      const fileInput = dropArea.querySelector('.fileInput');
      if (!fileInput) {
        console.error('File input element not found!');
        return;
      }

      fileInput.addEventListener('change', (event) => {
        const files = event.target.files;
        this.handleFiles(files, dropAreaIndex);
      });

      // Trigger the file input
      fileInput.click();
    },
    handleFiles(files) {
      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        this.files.push(file);
        // Don't process the file immediately, only when upload button is clicked
      }
    },
    async uploadFile(file) {
      this.loadFlag = true;
      // 如果文件已经在上传中，则不允许再次上传
      if (file.uploading) {
        return;
      }

      // 设置上传按钮为不可点击
      this.uploadButtonDisabled = true;

      // 设置文件为上传中状态
      file.uploading = true;

      // 处理和上传文件
      try {
        await this.processFile(file);
        // await this.RecordUpload(file, this.dropIndex);
        file.uploadedSuccessfully = true; // 标记文件上传成功
      } catch (error) {
        console.error("上传失败:", error);
      } finally {
        // 无论上传成功或失败，都需要恢复上传按钮为可点击状态
        this.uploadButtonDisabled = false;
        file.uploading = false; // 上传结束，重置上传中状态
      }
    },
    // 检查上传按钮是否可点击
    isUploadButtonDisabled(file) {
      return this.uploadButtonDisabled || file.uploadedSuccessfully || file.uploading;
    },
    async processFile(file) {
      this.list_P = [];
      this.list_R = [];
      const reader = new FileReader();
      this.resultFlag = true;

      reader.onload = async (e) => {
        const data = new Uint8Array(e.target.result);
        const workbook = XLSX.read(data, {type: 'array'});
        const sheetName = workbook.SheetNames[0];
        const worksheet = workbook.Sheets[sheetName];
        const jsonData = XLSX.utils.sheet_to_json(worksheet, {header: 1});

        // 检查第一行是否为空
        if (jsonData.length > 0 && jsonData[0].every(cell => cell === null || cell === '')) {
          this.$message({
            message: '第一行不能为空，请填写表头',
            type: 'error'
          });
          return; // 不进行后续处理
        }

        // 定义列名到参数名称的映射关系
        const columnMapping = {
          '代理': 'proxy',
          '客户名字': 'customer',
          '件数': 'count',
          '总毛重KG': 'weight',
          '产品名称': 'product_name',
          '快递单号': 'tracking_number',
          '柜号': 'counter_number',
          '状态': 'state',
          '更新时间': 'op_time'
          // 其他列名和参数名称的映射关系
        };

        // 定义一个数组来保存每行数据的上传结果
        const uploadResults = [];

        // 遍历行数据，构造请求参数并发送到后端
        for (let i = 1; i < jsonData.length; i++) {
          const row = jsonData[i];

          // console.log(row);

          const data = {};

          // 根据映射关系构造请求参数
          for (let j = 0; j < row.length; j++) {
            const columnName = jsonData[0][j]; // 获取Excel表格的列名
            const paramName = columnMapping[columnName]; // 根据映射关系获取后端参数名
            data[paramName] = row[j]; // 使用后端参数名作为键
          }

          // 添加当前操作时间的年、月、日、时、分、秒
          const currentTime = new Date();
          data['op_time'] = currentTime.getFullYear() + '-' +
            ('0' + (currentTime.getMonth() + 1)).slice(-2) + '-' +
            ('0' + currentTime.getDate()).slice(-2) + ' ' +
            ('0' + currentTime.getHours()).slice(-2) + ':' +
            ('0' + currentTime.getMinutes()).slice(-2) + ':' +
            ('0' + currentTime.getSeconds()).slice(-2);


          data['username'] = this.$route.query.currentUserName;


          this.cleanString(data['tracking_number'])
            .then((result) => {
              data['tracking_number'] = result;
            })
          this.cleanString(data['counter_number'])
            .then((result) => {
              data['counter_number'] = result;
            })


          this.list_P.push(data);

          // console.log("data");
          // console.log(data);
        }
        let result;
        // console.log("operationType");
        // console.log(operationType);


        this.sendInsertDataToBackend(this.list_P, file);

        // 将每行数据的上传结果保存到数组中
        console.log(this.list_P);
        uploadResults.push(result);


        for (let n = 0; n < uploadResults.length; n++) {
          if (uploadResults[n].success != true) {

            this.resultFlag = false;
            this.$message({ // 使用 this.$message 并且是小写的 m
              message: '第' + (n + 1) + "行导入错误",
              type: 'error'
            });
            this.loadFlag = false;
          }
        }
        if (this.resultFlag === true) {
          // 输出上传成功消息
          this.$message({ // 使用 this.$message 并且是小写的 m
            message: '所有文件上传成功',
            type: 'success'
          });
          this.loadFlag = false;
          this.files = [];
        }

        this.files = [];


      };

      reader.readAsArrayBuffer(file);

      ////////////////record 插入///////////////////
      // const uploadResults2 = [];
      // const reader2 = new FileReader();
      // // this.resultFlag_r = true;

      // reader2.onload = async (e) => {
      //   const data = new Uint8Array(e.target.result);
      //   const workbook = XLSX.read(data, { type: 'array' });
      //   const sheetName = workbook.SheetNames[0];
      //   const worksheet = workbook.Sheets[sheetName];
      //   const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

      //   // 定义列名到参数名称的映射关系
      //   const columnMapping2 = {
      //     '产品名称': 'product_name',
      //     '快递单号': 'tracking_number',
      //     '柜号': 'counter_number',
      //     '状态': 'update_state',
      //     // 其他列名和参数名称的映射关系
      //   };


      //   // 遍历行数据，构造请求参数并发送到后端
      //   for (let i = 1; i < jsonData.length; i++) {
      //     const row = jsonData[i];

      //     // 如果第一个单元格为空，表示空行，直接跳过
      //     if (!row[0]) {
      //       continue;
      //     }

      //     const data2 = {};

      //     // 根据映射关系构造请求参数
      //     for (let j = 0; j < row.length; j++) {
      //       const columnName = jsonData[0][j]; // 获取Excel表格的列名
      //       const paramName = columnMapping2[columnName]; // 根据映射关系获取后端参数名
      //       data2[paramName] = row[j]; // 使用后端参数名作为键
      //     }

      //     // 添加当前操作时间的年、月、日、时、分、秒
      //     const currentTime = new Date();
      //     data2['time_operating'] = currentTime.getFullYear() + '-' +
      //                       ('0' + (currentTime.getMonth() + 1)).slice(-2) + '-' +
      //                       ('0' + currentTime.getDate()).slice(-2) + ' ' +
      //                       ('0' + currentTime.getHours()).slice(-2) + ':' +
      //                       ('0' + currentTime.getMinutes()).slice(-2) + ':' +
      //                       ('0' + currentTime.getSeconds()).slice(-2);

      //     data2['manager'] = this.currentUserName;

      //     // 根据操作类型决定发送数据到后端的逻辑
      //     let result2;
      //     // console.log("Recorddata:");
      //     // console.log(data);


      //     // result2 = await this.sendInsertDataToRecord(data2);


      //     // console.log("data2:");
      //     // console.log(data2);
      //     //   console.log("this.list_R");
      //     //   console.log(this.list_R);


      //     this.list_R.push(data2);
      //     // 将每行数据的上传结果保存到数组中
      //     uploadResults2.push(result2);


      //   }
      //   console.log("this.list_R");
      //     console.log(this.list_R);
      //   result2 = await this.sendInsertDataToRecord(this.list_R);
      //   // console.log("result2");
      //   //   console.log(result2);

      //         };

      //   reader2.readAsArrayBuffer(file);


    },


    async sendInsertDataToBackend(data_p, file) {

      // console.log("sendInsertDataToBackend成功");
      // console.log("sendInsertDataToBackend收到的data_p:");
      // console.log(data_p);
      // 发送数据到后端的逻辑，插入操作
      axios.post('https://www.hxlogistics.top/to_url/Product/insert/', data_p)
        .then((res) => {
          //  console.log("进入record");
          //  console.log("product_res");
          //  console.log(res);
          //  if(res.data==="Insert all product!")
          //  {
          //   console.log("res.data");
          //  console.log(res.data);
          this.insertFlag = true;
          // console.log("this.insertFlag");
          //     console.log(this.insertFlag);
          //  }
          //record插入
          // const uploadResults2 = [];
          const reader2 = new FileReader();
          // this.resultFlag_r = true;

          reader2.onload = async (e) => {
            const data = new Uint8Array(e.target.result);
            const workbook = XLSX.read(data, {type: 'array'});
            const sheetName = workbook.SheetNames[0];
            const worksheet = workbook.Sheets[sheetName];
            const jsonData = XLSX.utils.sheet_to_json(worksheet, {header: 1});

            // 定义列名到参数名称的映射关系
            const columnMapping2 = {
              '产品名称': 'product_name',
              '快递单号': 'tracking_number',
              '柜号': 'counter_number',
              '状态': 'update_state',
              // 其他列名和参数名称的映射关系
            };


            // 遍历行数据，构造请求参数并发送到后端
            for (let i = 1; i < jsonData.length; i++) {
              const row = jsonData[i];


              const data2 = {};

              // 根据映射关系构造请求参数
              for (let j = 0; j < row.length; j++) {
                const columnName = jsonData[0][j]; // 获取Excel表格的列名
                const paramName = columnMapping2[columnName]; // 根据映射关系获取后端参数名
                data2[paramName] = row[j]; // 使用后端参数名作为键
              }

              // 添加当前操作时间的年、月、日、时、分、秒
              const currentTime = new Date();
              data2['time_operating'] = currentTime.getFullYear() + '-' +
                ('0' + (currentTime.getMonth() + 1)).slice(-2) + '-' +
                ('0' + currentTime.getDate()).slice(-2) + ' ' +
                ('0' + currentTime.getHours()).slice(-2) + ':' +
                ('0' + currentTime.getMinutes()).slice(-2) + ':' +
                ('0' + currentTime.getSeconds()).slice(-2);

              data2['manager'] = this.$route.query.currentUserName;

              // 根据操作类型决定发送数据到后端的逻辑
              // let result2;
              // console.log("Recorddata:");
              // console.log(data);


              // result2 = await this.sendInsertDataToRecord(data2);


              // console.log("data2[tracking number]:");
              // console.log(data2['tracking_number']);
              //   console.log("this.list_R");
              //   console.log(this.list_R);


// data['counter_number']
              // data2['tracking_number'] =  data2['tracking_number'].replace(/ /g, '');
              // data2['counter_number'] =  data2['counter_number'].replace(/ /g, '');

              this.cleanString(data2['tracking_number'])
                .then((result) => {
                  data2['tracking_number'] = result;
                })
              this.cleanString(data2['counter_number'])
                .then((result) => {
                  data2['counter_number'] = result;
                })
              // console.log(data2['tracking_number']);

              // console.log("data2[tracking number]:");
              // console.log(data2['tracking_number']);

              // console.log("读取data中");
              this.list_R.push(data2);
              // 将每行数据的上传结果保存到数组中
              // uploadResults2.push(result2);


            }
            // console.log("this.list_R_删除前");
            // console.log(this.list_R);

            // let i = 0;
            // for( i = res.data.length-1;i>=0;i--){//遍历product_list插入的返回值
            //     // if(res.data[i] == "Insert all product!"){
            //     //   // console.log("update");
            //     // }
            //     // if(res.data[i] =="Product Insert!"){
            //     //   // console.log("insert");

            //     // }
            //   if(res.data[i] =="Product Error!"){
            //     // console.log("error");
            //     this.list_R.splice(i,1);//record_list删除
            //   }
            //   }

            // console.log("this.list_R删除后");
            // console.log(this.list_R);
            let result2 = '';
            result2 = await this.sendInsertDataToRecord(this.list_R);
            // console.log("result_R");
            //   console.log(result2);
            // console.log("result2");
            //   console.log(result2);
            //   console.log(result2.response);
            //   console.log(result2.response.data);
            if (result2.response.data === "Insert all record!") {
              // console.log("result2.data");
              // console.log(result2.data);
              this.insertFlag_R = true;
              // console.log("this.insertFlag_R");
              // console.log(this.insertFlag_R);
            }

            if (this.insertFlag_R === true && this.insertFlag === true) {
              this.$message({
                message: "上传成功",
                type: 'success'
              });
              this.loadFlag = false;
              this.deleteFile(file);
            } else {
              this.$message({
                message: "上传失败",
                type: 'error'
              });
              this.loadFlag = false;
            }
          };

          reader2.readAsArrayBuffer(file);


        })
        .catch((err) => {
          if (err.response && err.response.status === 401) {
            this.$message({
              message: "上传错误",
              type: 'error'
            });
            this.loadFlag = false;
          } else {
            this.$message({
              message: "网络错误",
              type: 'error'
            });
            this.loadFlag = false;
          }
        });
      // console.log("InsertDate:");
      // console.log(data);
      // let res_op = [];//遍历response,如果i为error则删除list_R对应的i,删除完后继续插入
      //record插入代码


    },

    // async sendUpdateDataToBackend(data) {
    //   console.log("sendUpdateDataToBackend成功");
    //   try {
    //     // 发送数据到后端的逻辑，更新操作
    //     const response = await axios.put('http://47.98.58.79:8080/Product', data);
    //     return { success: response.product_id !== -1, response };
    //   } catch (error) {
    //     return { success: false, error };
    //   }
    // },

    showUploadResult(uploadResults) {
      const failedUploads = uploadResults.filter(result => !result.success);
      // console.log("showUploadResult成功");
      if (failedUploads.length === 0) {
        // 所有文件上传成功
        this.showSuccessMessage();
      } else {
        // 有文件上传失败
        this.showFailedMessage(failedUploads);
      }

    },

    showSuccessMessage() {
      // 显示成功提示
      const index = this.files.findIndex(f => f.name === file.name);
      if (index !== -1) {
        this.$set(this.files[index], 'uploadedSuccessfully', true);
        setTimeout(() => {
          // 2秒后隐藏成功提示
          this.$set(this.files[index], 'uploadedSuccessfully', false);
        }, 2000);
      }
    },

    showFailedMessage(failedUploads) {
      // 显示失败提示
      failedUploads.forEach(failedUpload => {
        console.error(`Upload failed for tracking number ${failedUpload.response.tracking_number}: ${failedUpload.error}`);
      });
    },

    removeUploadedFile(file) {
      const index = this.files.findIndex(f => f.name === file.name);
      if (index !== -1) {
        this.files.splice(index, 1);
      }
    },
    //操作记录
    //  async RecordUpload(file, operationType) {
    //     const reader = new FileReader();
    //     this.resultFlag = true;

    //     reader.onload = async (e) => {
    //       const data = new Uint8Array(e.target.result);
    //       const workbook = XLSX.read(data, { type: 'array' });
    //       const sheetName = workbook.SheetNames[0];
    //       const worksheet = workbook.Sheets[sheetName];
    //       const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

    //       // 定义列名到参数名称的映射关系
    //       const columnMapping = {
    //         '产品名称': 'product_name',
    //         '快递单号': 'tracking_number',
    //         '柜号': 'counter_number',
    //         '状态': 'update_state',
    //         // 其他列名和参数名称的映射关系
    //       };

    //       // 定义一个数组来保存每行数据的上传结果
    //       const uploadResults = [];

    //       // 遍历行数据，构造请求参数并发送到后端
    //       for (let i = 1; i < jsonData.length; i++) {
    //         const row = jsonData[i];

    //         // 如果第一个单元格为空，表示空行，直接跳过
    //         if (!row[0]) {
    //           continue;
    //         }

    //         const data = {};

    //         // 根据映射关系构造请求参数
    //         for (let j = 0; j < row.length; j++) {
    //           const columnName = jsonData[0][j]; // 获取Excel表格的列名
    //           const paramName = columnMapping[columnName]; // 根据映射关系获取后端参数名
    //           data[paramName] = row[j]; // 使用后端参数名作为键
    //         }

    //         // 添加当前操作时间的年、月、日、时、分、秒
    //         const currentTime = new Date();
    //         data['time_operating'] = currentTime.getFullYear() + '-' +
    //                           ('0' + (currentTime.getMonth() + 1)).slice(-2) + '-' +
    //                           ('0' + currentTime.getDate()).slice(-2) + ' ' +
    //                           ('0' + currentTime.getHours()).slice(-2) + ':' +
    //                           ('0' + currentTime.getMinutes()).slice(-2) + ':' +
    //                           ('0' + currentTime.getSeconds()).slice(-2);

    //         data['manager'] = this.currentUserName;

    //         // 根据操作类型决定发送数据到后端的逻辑
    //         let result;
    //         // console.log("Recorddata:");
    //         // console.log(data);


    //         result = await this.sendInsertDataToRecord(data);
    //         // console.log("result");
    //         // console.log(result);


    //         // 将每行数据的上传结果保存到数组中
    //         uploadResults.push(result);

    //       }

    //       for(let n = 0 ; n<uploadResults.length ; n++){
    //         if(uploadResults[n].success!=true){

    //           // this.resultFlag=false;
    //           // this.$message({ // 使用 this.$message 并且是小写的 m
    //           //   message: '第'+(n-1)+"行导入错误",
    //           //   type: 'error'
    //           // });
    //         }
    //       }
    //       if(this.resultFlag === true){
    //               // 输出上传成功消息
    //         // this.$message({ // 使用 this.$message 并且是小写的 m
    //         //   message: '所有文件上传成功',
    //         //   type: 'success'
    //         // });
    //         // this.files = [];
    //       }

    //       // this.files = [];


    //     };

    //     reader.readAsArrayBuffer(file);
    //   },

    async sendInsertDataToRecord(list) {

      //  console.log("R接收到的list:");
      //     console.log(list);
      try {
        // console.log("sendInsertDataToRecord成功");
        // 发送数据到后端的逻辑，插入操作
        const response = await axios.post('https://www.hxlogistics.top/to_url/Record/insert/', list);
        // console.log(response);
        return {success: response.record_id !== -1, response};
      } catch (error) {
        return {success: false, error};
      }
    },

    downloadExcel(dropArea) {
      // 下载Excel文件的逻辑
      window.open(dropArea.fileUrl);
    },
    downloadFile(file) {
      const url = URL.createObjectURL(file);
      const link = document.createElement('a');
      link.href = url;
      link.download = file.name;
      link.click();
      URL.revokeObjectURL(url);
    },
    deleteFile(file) {
      const index = this.files.indexOf(file);
      if (index !== -1) {
        this.files.splice(index, 1);
      }
    },
    downloadExcel() {
      // 创建Excel数据
      const data = [
        ['代理', '客户名字', '件数', '总毛重KG', '产品名称', '快递单号', '柜号', '状态', '更新时间', ' '],
        ['HXlogistics', '客户', 1, 1.4, '生活用品', 'ABCD123456789', 'ABCD123456789', '已入库', '2024年3月15日', '示例，上传时删除该行，更新时间仅在操作失误时填写，默认为空即可']

      ];

      // 将数据转换为Excel文件
      const worksheet = XLSX.utils.aoa_to_sheet(data);
      const workbook = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');
      const excelBuffer = XLSX.write(workbook, {bookType: 'xlsx', type: 'array'});

      // 创建Blob对象
      const blob = new Blob([excelBuffer], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});

      // 创建下载链接并点击触发下载
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', '物流更新模板.xlsx');
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    }

  }

};
</script>


<style scoped>
/****************** 总容器 ******************/
.wrapper {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
}

.wrapper .dropContainer {
  position: absolute;
  width: 80%;
  margin-left: 20%;
  margin-top: 5%;

}

.wrapper .dropAreaContainer {

  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.wrapper .UpdateDiv {

  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}


.wrapper .fileName {
  position: absolute;
  bottom: 100px;
  right: 0;
  /* 设置文件名显示在右下角 */
}

.wrapper .downloadButton {
  position: absolute;
  bottom: 0;
  right: 0;
  /* 设置下载按钮显示在右下角 */
}

.wrapper .file-download {
  position: fixed;
  right: 20px; /* 调整位置 */
  top: 30px; /* 调整位置 */
  width: 6%;
  height: 10vh;
  box-shadow: 0px 0 10px rgba(0, 89, 255, 0.5);
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 10px;
  z-index: 999; /* 确保位于其他元素之上 */
}

.wrapper .file-download p {
  color: grey;
  font-size: 5mm;
  font-weight: 500;
}

.wrapper .file-download div {
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 5px;
}


.wrapper .file-download div div {
  margin-top: 2px;
  width: 100%;
  height: 4vh;
  color: darkgrey;
  font-size: 5mm;
  border: 1px solid #e7e7e7;
  text-align: center;
  border: 1px dashed #ccc; /* 虚线边框 */
  border-radius: 2px;
}

.wrapper .file-download div div:hover {
  border-color: #007bff;
  color: #007bff;
}


.wrapper .title {
  color: #333;
  font-size: 6mm;
  margin-top: 3%;
}

.wrapper .UpdateTitle {
  color: #333;
  font-size: 6mm;
  margin-top: 3%;
}


.wrapper .dropArea,
.wrapper .UpdateBody {
  width: 50%;
  height: 25vh;
  border: 2px dashed #ccc;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin-top: 2%;
  cursor: pointer;
  color: grey;
}

.wrapper .UpdateBody {
  flex-direction: row;
}

.wrapper .UpdateBody p {
  flex: 0.1;
  font-size: 4mm;
  font-weight: 500;
}

.wrapper .UpdateBody .switcher {
  flex: 0.3;
  height: 5vh;
}

.wrapper .UpdateBody #status-switcher {
  height: 100%;
  width: 50%;
  padding: 1vh;
  border: 1px solid #007bff;
  border-radius: 5px;
}

.wrapper .UpdateBody input {
  flex: 0.3;
  height: 4.5vh;
  padding: auto;
  width: 50%;
  font-size: 4mm;
}

.wrapper .UpdateBody button {
  flex: 0.15;
  height: 5vh;
  border: none;
  outline: none;
  background-color: #007bff;
  color: #fff;
  border-radius: 10px;
}

.wrapper .dropArea svg {
  margin-top: 10px;
}

.wrapper .UpdateBody button:hover {
  background-color: #a9cdf6;
  color: #007bff;
}

.wrapper .dropArea:hover {
  border-color: #007bff;
}

.download-button {
  position: absolute;
  margin-left: 45%;
  margin-top: 30px;
  font-size: 5mm;
}

.download-button button {
  flex-direction: row;
  margin-left: 4px;
  border: none;
  outline: none;
  margin-top: 1%;
  width: 15%;
  height: 4vh;
}

.download-button button:hover {
  color: #fff;
  background-color: #007bff;
}

.box {
  position: fixed;
  text-align: center;
  width: 100%;
  height: 100%;
  /* margin-left:40% ;
  margin-top: 20%; */
  background-color: rgba(217, 216, 216, 0.538);
}

.box > span {
  animation: loader 1000ms infinite linear;
  border-radius: 100%;
  border: 6px solid #2dbb55;
  border-left-color: transparent;
  color: transparent;
  display: inline-block;
  line-height: 1.2;
  width: 50px;
  height: 50px;
  margin-top: 25%;
}

@keyframes loader {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
