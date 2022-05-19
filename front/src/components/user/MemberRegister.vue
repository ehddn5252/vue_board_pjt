<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <section class="signup-container text-left">
      <div class="registercontainer">
        <div class="title">회원가입</div>
        <div class="content">
          <form action="/user/register" method="post">
            <div class="user-details">
              <div class="input-box">
                <span class="details">성함</span>
                <input
                  type="text"
                  name="userName"
                  placeholder="Enter your name"
                  required
                />
              </div>
              <div class="input-box">
                <span class="details">전화번호</span>
                <input
                  type="text"
                  name="phoneNum"
                  placeholder="Enter your phone number"
                  required
                />
              </div>
              <div class="input-box">
                <span class="details">이메일</span>
                <input
                  type="email"
                  name="userEmail"
                  placeholder="Enter your email"
                  required
                />
              </div>
              <div class="input-box">
                <span class="details">생일</span>
                <input type="date" name="userBirth" required />
              </div>
              <div class="input-box">
                <span class="details">아이디</span>
                <input
                  id="registId"
                  type="text"
                  name="userId"
                  placeholder="Enter your id"
                  required
                  autocomplete="off"
                />
                <div id="idresult" class="mt-1"></div>
              </div>
              <div class="input-box">
                <span class="details">비밀번호</span>
                <input
                  type="password"
                  name="userPwd"
                  placeholder="Enter your password"
                  required
                />
              </div>
            </div>
            <div class="gender-details">
              <input type="radio" name="userGender" id="dot-1" value="남" />
              <input type="radio" name="userGender" id="dot-2" value="여" />
              <input type="radio" name="userGender" id="dot-3" value="비밀" />
              <span class="gender-title">성별</span>
              <div class="category">
                <label for="dot-1">
                  <span class="dot one"></span>
                  <span class="gender">남</span>
                </label>
                <label for="dot-2">
                  <span class="dot two"></span>
                  <span class="gender">여</span>
                </label>
                <label for="dot-3">
                  <span class="dot three"></span>
                  <span class="gender">비밀</span>
                </label>
              </div>
            </div>
            <div class="button">
              <input type="submit" id="registerBtn" value="제출" />
            </div>
          </form>
        </div>
      </div>
    </section>
  </b-container>
</template>

<script>
export default {
  name: "MemberRegister",
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
