import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";

// import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    sidos: [{ value: null, text: "시도 선택" }],
    guguns: [{ value: null, text: "구군 선택" }],
    dongs: [{ value: null, text: "동 선택" }],
    houses: [],
    house: null,
    aroundStore: null,
    aroundStores: [],
    checkedStore: null,
    checkedStoreList: [],

    todos: [
      // { title: '할 일1', completed: false },
      // { title: '할 일2', completed: false },
    ],
  },
  getters: {
    aroundStoreLonLat(state) {
      var arr = new Array(state.aroundStores.length);
      for (var i = 0; i < arr.length; ++i) {
        var latlon = [state.aroundStores[i].lat, state.aroundStores[i].lon];
        arr[i] = latlon;
      }
      return arr;
    },

    allTodosCount(state) {
      return state.todos.length;
    },
    completedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === true;
      }).length;
    },
    unCompletedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === false;
      }).length;
    },
  },
  mutations: {
    /////////////////////////////// House start /////////////////////////////////////
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },

    SET_AROUND_STORE_LIST(state, aroundStores) {
      console.log(" in SET_AROUND_STORE_LIST");

      aroundStores.forEach((aroundStore) => {
        state.aroundStores.push({
          title: "제목",
          bizesId: aroundStore.bizesId, // "10142096",
          bizesNm: aroundStore.bizesNm, //"카페비스타",
          brchNm: aroundStore.brchNm, //: "리베라호텔서울",
          indsLclsCd: aroundStore.indsLclsCd, //: "Q",
          indsLclsNm: aroundStore.indsLclsNm, //: "음식",
          indsMclsCd: aroundStore.indsMclsCd, //"indsMclsCd": "Q12",
          indsMclsNm: aroundStore.indsMclsNm, //: "커피점/카페",
          indsSclsCd: aroundStore.indsSclsCd, //: "Q12A01",
          indsSclsNm: aroundStore.indsSclsNm, //: "커피전문점/카페/다방",
          ksicCd: aroundStore.ksicCd, //: "I56220",
          ksicNm: aroundStore.ksicNm, //: "비알콜 음료점업",
          ctprvnCd: aroundStore.ctprvnCd, //: "11",
          ctprvnNm: aroundStore.ctprvnNm, //: "서울특별시",
          signguCd: aroundStore.signguCd, //: "11680",
          signguNm: aroundStore.signguNm, //: "강남구",
          // adongCd: "adongCd", //: "1168056500",
          // adongNm: "adongNm", //: "청담동",
          ldongCd: aroundStore.ldongCd, //: "1168010400",
          ldongNm: aroundStore.ldongNm, //: "청담동",
          lnoCd: aroundStore.lnoCd, //: "1168010400100530007",

          lnoAdr: aroundStore.lnoAdr, //: "서울특별시 강남구 청담동 53-7",
          rdnmCd: aroundStore.rdnmCd, //: "116802122002",
          rdnm: aroundStore.rdnm, //: "서울특별시 강남구 영동대로",
          //"bldMngNo": "1168010400100530007017901",
          bldNm: aroundStore.bldNm, //: "호텔리베라",
          rdnmAdr: aroundStore.rdnmAdr, //: "서울특별시 강남구 영동대로 737",
          lon: aroundStore.lon, //: ,//127.054169818602,
          lat: aroundStore.lat, //: 37.5237556463502
          adongCd: aroundStore.bizesNm,
          adongNm: aroundStore.adongNm,
          text: aroundStore.aroundStoreName,
        });
      });
    },

    SET_DETAIL_AROUND_STORE(state, aroundStore) {
      state.aroundStore = aroundStore;
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "시도 선택" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      console.log("Mutations", house);
      state.house = house;
    },
    /////////////////////////////// House end /////////////////////////////////////

    //////////////////////////// Todo List start //////////////////////////////////
    CREATE_TODO(state, todoItem) {
      state.todos.push(todoItem);
    },
    DELETE_TODO(state, todoItem) {
      const index = state.todos.indexOf(todoItem);
      state.todos.splice(index, 1);
    },
    UPDATE_TODO_STATUS(state, todoItem) {
      state.todos = state.todos.map((todo) => {
        if (todo === todoItem) {
          return {
            ...todo,
            completed: !todoItem.completed,
          };
        }
        return todo;
      });
    },
    //////////////////////////// Todo List end //////////////////////////////////
  },
  actions: {
    /////////////////////////////// AroundStore start ///////////////////////////////
    getSidoAroundMap({ commit }) {
      http
        .get(`/apart/map/sido`)
        .then(({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugunAroundMap({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/apart/map/gugun`, { params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    /////////////////////////////// House start /////////////////////////////////////
    getSido({ commit }) {
      http
        .get(`/map/sido`)
        .then(({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/map/gugun`, { params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, gugunCode) {
      const params = { gugun: gugunCode };
      http
        .get(`/map/dong`, { params })
        .then(({ data }) => {
          console.log(data);
          console.log(commit);
          commit("SET_DONG_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getAroundStoreList({ commit }, datas) {
      //const params = { sido: sidoCode, sigugun: gugunCode, dong: dongCode };
      // const ctprvnCd = datas.sidoCode;
      const signguCd = datas.gugunCode;
      const adongCd = datas.dongCode;
      console.log(adongCd);
      // const SERVICE_KEY ="yOYPxjA2Luqpjh8gS0r0pw69WoBHUn5HXJzTznjhCK78Aab2ZiFJ5pAGNq%2FLoVzbI1pCfMG10RPiGyk%2BqfFAIQ%3D%3D"; //process.env.VUE_APP_APT_DEAL_API_KEY;
      const DECODE_SERVICE_KEY =
        "yOYPxjA2Luqpjh8gS0r0pw69WoBHUn5HXJzTznjhCK78Aab2ZiFJ5pAGNq/LoVzbI1pCfMG10RPiGyk+qfFAIQ==";
      // const SERVICE_KEY =
      //   "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
      // http://apis.data.go.kr/B553077/api/open/sdsc2/storeZoneInAdmi?divId=adongCd&key=1168069000

      // callback url
      // http://apis.data.go.kr/B553077/api/open/sdsc2/storeListInDong
      const SERVICE_URL =
        "http://apis.data.go.kr/B553077/api/open/sdsc2/storeListInDong";
      const params = {
        serviceKey: DECODE_SERVICE_KEY, //decodeURIComponent(SERVICE_KEY),
        key: signguCd,
        divId: "signguCd", //ctprvnCd, signguCd, adongCd
        type: "json",
        indsLclsCd: "Q",
        // indsMclsCd: "Q12",
        // indsSclsCd: "Q12A01",
        pageNo: 1,
        numOfRows: 100,
      };
      http
        .get(SERVICE_URL, { params })
        .then(({ data }) => {
          commit("SET_AROUND_STORE_LIST", data.body.items);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    // getAroundStoreList({ commit }, datas) {
    //   //const params = { sido: sidoCode, sigugun: gugunCode, dong: dongCode };
    //   const regionCode = datas.dongCode;
    //   const params = { regionCode: regionCode };
    //   const url = `/interestinfo/store/searchByCode/`;
    //   // `/codes/${this.chekcedStoreList}`;
    //   console.log();
    //   http
    //     .get(url, { params })
    //     .then(({ data }) => {
    //       // console.log(commit, response);
    //       commit("SET_DONG_LIST", data);
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //     });
    // },
    getHouseList({ commit }, gugunCode) {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      // const SERVICE_KEY =
      //   "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
      const SERVICE_URL =
        "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
      const params = {
        LAWD_CD: gugunCode,
        DEAL_YMD: "202203",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      http
        .get(SERVICE_URL, { params })
        .then(({ data }) => {
          // console.log(commit, data);
          commit("SET_HOUSE_LIST", data.response.body.items.item);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    detailHouse({ commit }, house) {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);
      commit("SET_DETAIL_HOUSE", house);
    },
    /////////////////////////////// House end /////////////////////////////////////

    //////////////////////////// Todo List start //////////////////////////////////

    // destructuring 활용
    createTodo({ commit }, todoItem) {
      commit("CREATE_TODO", todoItem);
    },
    deleteTodo({ commit }, todoItem) {
      commit("DELETE_TODO", todoItem);
    },
    updateTodoStatus({ commit }, todoItem) {
      commit("UPDATE_TODO_STATUS", todoItem);
    },
    //////////////////////////// Todo List end //////////////////////////////////
  },
  modules: {},
  // plugins: [
  //   createPersistedState({
  //     // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
  //     storage: sessionStorage,
  //   }),
  // ],
});
