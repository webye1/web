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
          <button @click="selectFile(index)" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; background: transparent; border: none;"></button>
          <svg t="1709129721238" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2313" width="60" height="60"><path d="M913.066667 947.2H110.933333a34.133333 34.133333 0 0 1-34.133333-34.133333V110.933333a34.133333 34.133333 0 0 1 34.133333-34.133333h802.133334a34.133333 34.133333 0 0 1 34.133333 34.133333v802.133334a34.133333 34.133333 0 0 1-34.133333 34.133333z m-768-68.266667h733.866666V145.066667H145.066667z" p-id="2314" fill="#51a4db"></path><path d="M297.813333 578.133333a34.133333 34.133333 0 0 1-24.149333-58.282666l214.186667-214.101334a34.133333 34.133333 0 0 1 48.298666 48.213334l-214.186666 214.186666a34.133333 34.133333 0 0 1-24.149334 9.984z" p-id="2315" fill="#1296db"></path><path d="M512 818.602667a34.133333 34.133333 0 0 1-34.133333-34.133334V329.813333a34.133333 34.133333 0 0 1 58.282666-24.064l214.186667 214.101334a34.133333 34.133333 0 0 1-48.298667 48.298666L546.133333 412.245333v372.224a34.133333 34.133333 0 0 1-34.133333 34.133334zM756.48 273.664h-488.96a34.133333 34.133333 0 0 1 0-68.266667h488.96a34.133333 34.133333 0 0 1 0 68.266667z" p-id="2316" fill="#1296db"></path></svg>
          点击此区域上传文件
          <input type="file" class="fileInput" style="display: none;" @change="handleFiles($event, dropArea.id)">
          <!-- 文件名显示在右下角 -->
          <div class="fileName" v-if="dropArea.fileName">{{ dropArea.fileName }}</div>
          <!-- 下载按钮显示在右下角 -->
          <button class="downloadButton" @click="downloadFile(dropArea.id)" v-if="dropArea.fileName">下载</button>
        </div>
      </div>
    </div>

    <!-- 文件模板部分 -->
    <div class="file-download">
      <p>文件模板</p>
      <div>
        <!-- 使用v-for循环显示文件下载链接 -->
        <div class="file_d"  @click="downloadExcel() "> 点击下载 </div>
      </div>
    </div>

    <!-- 文件列表展示 -->
    <div v-for="file in files" :key="file.name" class="download-button">
      <p>本次上传文件名：{{ file.name }}，大小：{{ file.size }}字节</p>
      <button @click="downloadFile(file)">下载</button>
      <button @click="uploadFile(file)" :disabled="isUploadButtonDisabled(file)">
        {{ file.uploading ? '上传中' : '上传' }}
      </button>

    </div>
  </div>
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
        { id: 1, title: '单号录入' },
        { id: 2, title: '包裹入柜' },
        { id: 3, title: '物流更新' },
      ],
      dropIndex: null,
      files: [], // 存储上传的文件信息
      dropzoneActive: false,
      dropAreaIndex:'',
      routerProp: {}, // 确保传递了正确的 router 对象
      routeProp: {}, // 确保传递了正确的 route 对象
      currentUserType:'',
      currentUserName:'',
      resultFlag:true,
      uploadButtonDisabled: false // 控制上传按钮是否可点击
    };
  },
  mounted() {
      // 页面加载时从localStorage读取用户类型
      if (localStorage.getItem('currentUserType') && localStorage.getItem('currentUserName')) {
                    this.currentUserType = localStorage.getItem('currentUserType');
                    this.currentUserName = localStorage.getItem('currentUserName');
        }
              
      else{
        /// 当组件挂载时，尝试从LocalStorage中获取保存的数据
        const savedData = JSON.parse(localStorage.getItem('userData'));
      if (savedData) {
        // 如果有保存的数据，则恢复到组件的data中
        this.currentUserType = savedData.currentUserType;
        this.currentUserName = savedData.currentUserName;
      }
      }
  },
  
  methods: {
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
    dragOver(event) {
      event.preventDefault();
    },
    dropFile(operationType) {
      return function(event) {
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
        this.handleFiles(files,dropAreaIndex);
      });

      // Trigger the file input
      fileInput.click();
    },
    handleFiles(files, operationType) {
      this.dropIndex = operationType; 
      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        this.files.push(file);
        // Don't process the file immediately, only when upload button is clicked
      }
    },
      async uploadFile(file) {
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
        await this.processFile(file, this.dropIndex);
        await this.RecordUpload(file, this.dropIndex);
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
    async processFile(file, operationType) {
      const reader = new FileReader();
      this.resultFlag = true;

      reader.onload = async (e) => {
        const data = new Uint8Array(e.target.result);
        const workbook = XLSX.read(data, { type: 'array' });
        const sheetName = workbook.SheetNames[0];
        const worksheet = workbook.Sheets[sheetName];
        const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

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

          // 如果第一个单元格为空，表示空行，直接跳过
          if (!row[0]) {
            continue;
          }

          const data = {};

          // 根据映射关系构造请求参数
          for (let j = 0; j < row.length; j++) {
            const columnName = jsonData[0][j]; // 获取Excel表格的列名
            const paramName = columnMapping[columnName]; // 根据映射关系获取后端参数名
            data[paramName] = row[j]; // 使用后端参数名作为键
          }
          if(data['op_time']){
            let excelDate = XLSX.SSF.parse_date_code(data['op_time']);
            if (excelDate) {
                // 获取年、月、日
                let year = excelDate.y;
                let month = excelDate.m;
                let day = excelDate.d;

                // 构建新的日期格式，形如"2024-3-1"
                let formattedDate = `${year}-${month}-${day}`;

                // 更新数据中的日期字段
                data['op_time'] = formattedDate + ' ' +
                                    ('0' + '00').slice(-2) + ':' +
                                    ('0' + '00').slice(-2) + ':' +
                                    ('0' + '00').slice(-2);
    }
            
          }
          

          // 根据操作类型决定发送数据到后端的逻辑
          let result;
          console.log("data['op_time']");
          console.log(data['op_time']);
          console.log("productdata:");
          console.log(data);
          if (operationType === 0) {
            result = await this.sendInsertDataToBackend(data);

          } else if (operationType === 1 || operationType === 2) {
            result = await this.sendUpdateDataToBackend(data);
          }

          // 将每行数据的上传结果保存到数组中
          uploadResults.push(result);

        }
        
        for(let n = 0 ; n<uploadResults.length ; n++){
          if(uploadResults[n].success!=true){
            
            this.resultFlag=false;
            this.$message({ // 使用 this.$message 并且是小写的 m
              message: '第'+(n+1)+"行导入错误",
              type: 'error'
            });
          }
        }
        if(this.resultFlag === true){
                // 输出上传成功消息
          this.$message({ // 使用 this.$message 并且是小写的 m
            message: '所有文件上传成功',
            type: 'success'
          });
          this.files = [];
        }

        this.files = [];

        
      };

      reader.readAsArrayBuffer(file);
    },


    async sendInsertDataToBackend(data) {
      try {
        console.log("sendInsertDataToBackend成功");
        // 发送数据到后端的逻辑，插入操作
        const response = await axios.post('http://47.98.58.79:8080/Product', data);
        // console.log("InsertDate:");
        // console.log(data);
        return { success: response.product_id !== -1, response };
      } catch (error) {
        return { success: false, error };
      }
    },

    async sendUpdateDataToBackend(data) {
      console.log("sendUpdateDataToBackend成功");
      try {
        // 发送数据到后端的逻辑，更新操作
        const response = await axios.put('http://47.98.58.79:8080/Product', data);
        return { success: response.product_id !== -1, response };
      } catch (error) {
        return { success: false, error };
      }
    },

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
   async RecordUpload(file, operationType) {
      const reader = new FileReader();
      this.resultFlag = true;

      reader.onload = async (e) => {
        const data = new Uint8Array(e.target.result);
        const workbook = XLSX.read(data, { type: 'array' });
        const sheetName = workbook.SheetNames[0];
        const worksheet = workbook.Sheets[sheetName];
        const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

        // 定义列名到参数名称的映射关系
        const columnMapping = {
          '产品名称': 'product_name',
          '快递单号': 'tracking_number',
          '柜号': 'counter_number',
          '状态': 'update_state',
          // 其他列名和参数名称的映射关系
        };

        // 定义一个数组来保存每行数据的上传结果
        const uploadResults = [];

        // 遍历行数据，构造请求参数并发送到后端
        for (let i = 1; i < jsonData.length; i++) {
          const row = jsonData[i];

          // 如果第一个单元格为空，表示空行，直接跳过
          if (!row[0]) {
            continue;
          }

          const data = {};

          // 根据映射关系构造请求参数
          for (let j = 0; j < row.length; j++) {
            const columnName = jsonData[0][j]; // 获取Excel表格的列名
            const paramName = columnMapping[columnName]; // 根据映射关系获取后端参数名
            data[paramName] = row[j]; // 使用后端参数名作为键
          }

          // 添加当前操作时间的年、月、日、时、分、秒
          const currentTime = new Date();
          data['time_operating'] = currentTime.getFullYear() + '-' +
                            ('0' + (currentTime.getMonth() + 1)).slice(-2) + '-' +
                            ('0' + currentTime.getDate()).slice(-2) + ' ' +
                            ('0' + currentTime.getHours()).slice(-2) + ':' +
                            ('0' + currentTime.getMinutes()).slice(-2) + ':' +
                            ('0' + currentTime.getSeconds()).slice(-2);

          data['manager'] = this.currentUserName;

          // 根据操作类型决定发送数据到后端的逻辑
          let result;
          // console.log("Recorddata:");
          // console.log(data);
          
         
          result = await this.sendInsertDataToRecord(data);
          // console.log("result");
          // console.log(result);
         

          // 将每行数据的上传结果保存到数组中
          uploadResults.push(result);

        }
        
        for(let n = 0 ; n<uploadResults.length ; n++){
          if(uploadResults[n].success!=true){
            
            // this.resultFlag=false;
            // this.$message({ // 使用 this.$message 并且是小写的 m
            //   message: '第'+(n-1)+"行导入错误",
            //   type: 'error'
            // });
          }
        }
        if(this.resultFlag === true){
                // 输出上传成功消息
          // this.$message({ // 使用 this.$message 并且是小写的 m
          //   message: '所有文件上传成功',
          //   type: 'success'
          // });
          // this.files = [];
        }

        // this.files = [];

        
      };

      reader.readAsArrayBuffer(file);
    },

    async sendInsertDataToRecord(data) {
      try {
        console.log("sendInsertDataToRecord成功");
        // 发送数据到后端的逻辑，插入操作
        const response = await axios.post('http://47.98.58.79:8080/Record', data);
        return { success: response.product_id !== -1, response };
      } catch (error) {
        return { success: false, error };
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
    downloadExcel() {
      // 创建Excel数据
      const data = [
        ['代理', '客户名字', '件数', '总毛重KG', '产品名称', '快递单号', '柜号', '状态', '更新时间',' '],
        ['HXlogistics', '客户', 1, 1.4, '生活用品', 'ABCD123456789', 'ABCD123456789', '已入库', '2024年3月15日','示例，上传时删除该行，更新时间仅在操作失误时填写，默认为空即可']
        
      ];

      // 将数据转换为Excel文件
      const worksheet = XLSX.utils.aoa_to_sheet(data);
      const workbook = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');
      const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });

      // 创建Blob对象
      const blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

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
.wrapper  {
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
}

.wrapper  .dropContainer{
  position: absolute;
  width: 80%;
  margin-left: 20%;
  margin-top: 5%;

}
.wrapper .dropAreaContainer{
  
  text-align: center;
	display: flex;
	flex-direction: column;
	align-items: center;
  width: 100%;
}

.wrapper  .fileName {
  position: absolute;
  bottom: 100px;
  right: 0;
  /* 设置文件名显示在右下角 */
}

.wrapper  .downloadButton {
  position: absolute;
  bottom: 0;
  right: 0;
  /* 设置下载按钮显示在右下角 */
}

.wrapper  .file-download {
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

.wrapper  .file-download p{
	color: grey;
	font-size: 5mm;
	font-weight: 500;
}

.wrapper  .file-download div{
	text-align: center;
	display: flex;
	flex-direction: column;
	align-items: center;
  margin-top: 5px;
}


.wrapper  .file-download div div{
	margin-top: 2px;
	width: 100%;
	height: 4vh;
	color: darkgrey;
	font-size: 5mm;
	border:1px solid #e7e7e7; 
	text-align: center;
	 border: 1px dashed #ccc; /* 虚线边框 */
	 border-radius: 2px;
}

.wrapper  .file-download div div:hover {
  border-color: #007bff;
  color: #007bff;
}


.wrapper .title{
  color: #333;
  font-size: 6mm;
	margin-top: 3%;
}


.wrapper  .dropArea {
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

.wrapper .dropArea svg{
	margin-top: 10px;
}

.wrapper .dropArea:hover {
  border-color: #007bff;
}

.download-button{
  position: absolute;
  margin-left: 45%;
  margin-top: 30px;
  font-size: 5mm;
}

.download-button button{
  flex-direction:row;
  margin-left: 4px;
  border: none;
  outline: none;
  margin-top: 1%;
  width: 15%;
  height: 4vh;
}

.download-button button:hover{
  color: #fff;
  background-color: #007bff;
}

</style>