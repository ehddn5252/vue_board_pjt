<
<template>
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
</template>
