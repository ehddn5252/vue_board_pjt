<template>
  <div class="registercontainer">
    <b-row class="title">
      <b-col style="text-align: left">
        <b-form @submit.stop.prevent @submit="onSubmit" @reset="onReset">
          <b-form-group
            class="user-details"
            id="userName-group"
            label="이름:"
            label-for="userName"
          >
            <b-form-input
              class="input-box"
              id="userName"
              v-model="userInfo.userName"
              type="text"
              required
              placeholder="이름 입력..."
            ></b-form-input>
          </b-form-group>
          <b-form-group id="userName-group" label="아이디:" label-for="userId">
            <b-form-input
              id="userId"
              class="input-box"
              v-model="userInfo.userId"
              type="text"
              required
              placeholder="아이디 입력..."
            ></b-form-input>
            <b-form-invalid-feedback :state="validation">
              Your user ID must be 5-12 characters long.
            </b-form-invalid-feedback>
            <b-form-valid-feedback :state="validation">
              Looks Good.
            </b-form-valid-feedback>
          </b-form-group>
          <b-form-group
            id="userPhone-group"
            label="전화번호:"
            label-for="phoneNum"
          >
            <b-form-input
              id="phoneNum"
              class="input-box"
              v-model="userInfo.phoneNum"
              type="text"
              required
              placeholder="전화번호 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            class="input-box"
            id="userPwd-group"
            label="비밀번호:"
            label-for="userPwd"
          >
            <b-form-input
              id="userPwd"
              class="input-box"
              v-model="userInfo.userPwd"
              type="text"
              required
              placeholder="비밀번호 입력..."
            ></b-form-input>
          </b-form-group>
          <b-form-group
            id="userEmail-group"
            label="이메일:"
            label-for="userEmail"
          >
            <b-form-input
              id="userEmail"
              v-model="userInfo.userEmail"
              type="text"
              required
              placeholder="이메일 입력..."
            ></b-form-input>
          </b-form-group>
          <b-form-group
            class="gender-details"
            id="userGender-group"
            label="성별:"
            label-for="userGender"
          >
            <b-form-select
              id="userGender"
              v-model="userInfo.userGender"
              class="mb-2 mr-sm-2 mb-sm-0"
              :options="[
                { text: 'Choose...', value: null },
                '남',
                '여',
                '비밀',
              ]"
              :value="null"
            ></b-form-select>
          </b-form-group>

          <b-form-group
            id="userBirth-group"
            label="생일:"
            label-for="userBirth"
          >
            <b-form-input
              id="userBirth"
              v-model="userInfo.userBirth"
              type="text"
              required
              placeholder="생일 입력..."
            ></b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary" class="m-1"
            >회원 가입 완료</b-button
          >
          <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
        </b-form>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "MemberRegister",

  data() {
    return {
      userInfo: {
        userId: "",
        userName: "",
        phoneNum: "",
        userEmail: "",
        userPwd: "",
        userGender: "",
        userBirth: "",
      },
      type: "",
    };
  },

  computed: {
    validation() {
      return (
        this.userInfo.userId.length > 4 && this.userInfo.userId.length < 13
      );
    },
  },

  methods: {
    onSubmit(event) {
      console.log("int onSubmit");
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.userInfo.userName &&
        ((msg = "이름 입력해주세요"),
        (err = false),
        this.$refs.userName.focus());
      err &&
        !this.userInfo.phoneNum &&
        ((msg = "휴대폰 번호를 입력해주세요"),
        (err = false),
        this.$refs.phoneNum.focus());
      err &&
        !this.userInfo.userEmail &&
        ((msg = "이메일 입력해주세요"),
        (err = false),
        this.$refs.userEmail.focus());
      err &&
        !this.userInfo.userPwd &&
        ((msg = "패스워드를 입력해주세요"),
        (err = false),
        this.$refs.userPwd.focus());
      err &&
        !this.userInfo.userGender &&
        ((msg = "성별을 입력해주세요"),
        (err = false),
        this.$refs.userGender.focus());
      err &&
        !this.userInfo.userBirth &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.userBirth.focus());
      err &&
        !this.userInfo.userId &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());

      if (!err) alert(msg);
      else {
        console.log("this.registerUserInfo() is called");
        this.registUserInfo();
      }
    },

    onReset(event) {
      event.preventDefault();
      this.userInfo.userId = "";
      this.userInfo.userName = "";
      this.userInfo.phoneNum = "";
      this.userInfo.userEmail = "";
      this.userInfo.userPwd = "";
      this.userInfo.userGender = "";
      this.userInfo.userBirth = "";
      // this.$router.push({ name: "signUp" });
    },

    registUserInfo() {
      console.log("in registerUserInfo");
      http
        .post(`/user/register`, {
          userId: this.userInfo.userId,
          userName: this.userInfo.userName,
          phoneNum: this.userInfo.phoneNum,
          userEmail: this.userInfo.userEmail,
          userPwd: this.userInfo.userPwd,
          userGender: this.userInfo.userGender,
          userBirth: this.userInfo.userBirth,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    moveList() {
      this.$router.push({ name: "signUp" });
    },
  },
};
</script>

<style scoped>
/* @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap"); */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}

.registercontainer {
  margin: auto;
  max-width: 700px;
  width: 100%;
  background-color: #fff;
  padding: 25px 30px;
  border-radius: 5px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.15);
}
.registercontainer .title {
  font-size: 25px;
  font-weight: 500;
  position: relative;
}
.registercontainer .title::before {
  content: "";
  position: absolute;
  left: 0;
  bottom: 0;
  height: 3px;
  width: 30px;
  border-radius: 5px;
  background: linear-gradient(135deg, #71b7e6, #9b59b6);
}
.content form .user-details {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin: 20px 0 12px 0;
}
form .user-details .input-box {
  margin-bottom: 15px;
  width: calc(100% / 2 - 20px);
}
form .input-box span.details {
  display: block;
  font-weight: 500;
  margin-bottom: 5px;
}
.user-details .input-box input {
  height: 45px;
  width: 100%;
  outline: none;
  font-size: 16px;
  border-radius: 5px;
  padding-left: 15px;
  border: 1px solid #ccc;
  border-bottom-width: 2px;
  transition: all 0.3s ease;
}
.user-details .input-box input:focus,
.user-details .input-box input:valid {
  border-color: #9b59b6;
}
form .gender-details .gender-title {
  font-size: 20px;
  font-weight: 500;
}
form .category {
  display: flex;
  width: 80%;
  margin: 14px 0;
  justify-content: space-between;
}
form .category label {
  display: flex;
  align-items: center;
  cursor: pointer;
}
form .category label .dot {
  height: 18px;
  width: 18px;
  border-radius: 50%;
  margin-right: 10px;
  background: #d9d9d9;
  border: 5px solid transparent;
  transition: all 0.3s ease;
}
#dot-1:checked ~ .category label .one,
#dot-2:checked ~ .category label .two,
#dot-3:checked ~ .category label .three {
  background: #9b59b6;
  border-color: #d9d9d9;
}
form input[type="radio"] {
  display: none;
}
form .button {
  height: 45px;
  margin: 35px 0;
}
form .button input {
  height: 100%;
  width: 100%;
  border-radius: 5px;
  border: none;
  color: #fff;
  font-size: 18px;
  font-weight: 500;
  letter-spacing: 1px;
  cursor: pointer;
  transition: all 0.3s ease;
  background: #1894e7;
}
form .button input:hover {
  /* transform: scale(0.99); */
  background: #5b85a4;
}
form .button input:disabled {
  cursor: default;
  background: #c0c0c0;
}
@media (max-width: 584px) {
  .container {
    max-width: 100%;
  }
  form .user-details .input-box {
    margin-bottom: 15px;
    width: 100%;
  }
  form .category {
    width: 100%;
  }
  .content form .user-details {
    max-height: 300px;
    overflow-y: scroll;
  }
  .user-details::-webkit-scrollbar {
    width: 5px;
  }
}
@media (max-width: 459px) {
  .container .content .category {
    flex-direction: column;
  }
}

.findPwd-btn {
  display: block;
  width: 100%;
  height: 40px;
  color: #f1f1f1;
  background: rgb(0, 152, 212);
  background-size: 150%;
  outline: none;
  cursor: pointer;
  transition: 0.5s;
  border: none;
  border-radius: 5px;
}

.findPwd-btn:hover,
.findPwd-btn:active,
.findPwd-btn:focus {
  background: #50a3a2;
}

.findpwd-form {
  position: relative;
  z-index: 1;
  background: #ffffff;
  max-width: 800px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
section {
  padding: 40px 0 100px;
}
</style>
