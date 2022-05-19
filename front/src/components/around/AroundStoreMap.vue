<template>
  <div class="map_wrap">
    <div
      id="map"
      style="width: 500px; height: 500px; position: relative; overflow: hidden"
    ></div>
    <div class="hAddr">
      <span class="title">지도중심기준 행정동 주소정보</span>
      <span id="centerAddr"></span>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "AroundStoreMap",
  data() {
    return {
      markerPositions1: [
        [33.452278, 126.567803],
        [33.452671, 126.574792],
        [33.451744, 126.572441],
      ],
      markerPositions2: [
        [37.499590490909185, 127.0263723554437],
        [37.499427948430814, 127.02794423197847],
        [37.498553760499505, 127.02882598822454],
        [37.497625593121384, 127.02935713582038],
        [37.49629291770947, 127.02587362608637],
        [37.49754540521486, 127.02546694890695],
        [37.49646391248451, 127.02675574250912],
      ],
      markers: [],
      infowindow: null,
    };
  },
  watch: {
    aroundStoreLonLat: function (val) {
      this.displayMarker(val);
    },
  },
  computed: {
    aroundStoreLonLat() {
      return this.$store.getters.aroundStoreLonLat;
    },
  },

  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    // this.CLEAR_SIDO_LIST();
  },
  methods: {
    ...mapActions(["getSido"]),
    // sidoList() {
    //   this.getSido();
    // },
    // gugunList() {
    //   console.log(this.sidoCode);
    //   this.CLEAR_GUGUN_LIST();
    //   this.gugunCode = null;
    //   if (this.sidoCode) this.getGugun(this.sidoCode);
    // }

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      console.log(markerPositions);
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map((position) => {
        return new kakao.maps.LatLng(...position);
      });

      if (positions.length > 0) {
        this.markers = positions.map((position) => {
          return new kakao.maps.Marker({ map: this.map, position });
        });
        console.log("positions");
        for (let i = 0; i < positions.length; ++i) {
          console.log(positions[i]);
        }
        var bounds = new kakao.maps.LatLngBounds();
        for (let i = 0; i < positions.length; i++) {
          bounds.extend(positions[i]);
        }
        //=========

        // const bounds = positions.reduce((bounds, latlng) => {
        //   console.log("bounds");
        //   console.log(bounds);
        //   console.log(latlng);

        //   bounds.extend(latlng);
        // }, new kakao.maps.LatLngBounds());

        this.map.setBounds(bounds);
      }
    },
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      console.log("initmap is woking");
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }
  },

  //   const script = document.createElement("script");
  //   // script.type = "text/javascript";
  //   script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_KAKAO_API_KEY}&libraries=services&autoload=false`;
  //   document.head.appendChild(script);
  //   const positions = this.$store.state.aroundStore;

  //   var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  //     mapOption = {
  //       center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
  //       level: 5, // 지도의 확대 레벨
  //     };

  //   var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

  //   // 마커 이미지의 이미지 주소입니다
  //   var imageSrc =
  //     "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

  //   for (var i = 0; i < positions.length; i++) {
  //     // 마커 이미지의 이미지 크기 입니다
  //     var imageSize = new kakao.maps.Size(24, 35);

  //     // 마커 이미지를 생성합니다
  //     var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

  //     // 마커를 생성합니다
  //     var marker = new kakao.maps.Marker({
  //       map: map, // 마커를 표시할 지도
  //       position: new kakao.maps.LatLng(positions[i].lat, positions[i].lng), //positions[i].latlng, // 마커를 표시할 위치
  //       title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
  //       image: markerImage, // 마커 이미지
  //     });
  //     console.log(marker);
  //   }
  //   map.setCenter(positions[0].latlng);
  // },
};
</script>

<style></style>
