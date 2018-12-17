package com.biz.woojjujju.service;

import android.os.AsyncTask;
import android.util.Log;

import com.biz.woojjujju.R;
import com.biz.woojjujju.config.GoDataKey;
import com.biz.woojjujju.models.GoDataListVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

//public class GoDataGetterService extends AsyncTask {

public class GoDataGetterService extends AsyncTask<String,String,List<GoDataListVO>> {

    int menu_id = 0 ;

    private String goAPIKey = GoDataKey.GoAPIkey;
    private String goURL = GoDataKey.GoAPIurl;
    private StringBuilder goDataResult;
    private List<GoDataListVO> goDataLists ;

    private static String CHAR_TRGTER_ARRAY = "charTrgterArray" ;
    private static String LIFE_ARRAY = "lifeArray" ;


//    public void getData() {
    private void getData(String searchId,String searchValue,String sv) {
        // Java String 변수를 생성하고 계속 이어붙이기를 하는 것은 성능상 문제가 있다
        /*
            String name = "홍기롱" ;
            name += "이몽룡" ;
            name += "성춘향" ;
         */
        StringBuilder urlBuilder = new StringBuilder(goURL);

//        필수코드
        try {
            // Service
            urlBuilder.append("?" + URLEncoder.encode("crtiKey", "UTF-8")
                    + "=" + goAPIKey); // URLEncoder.encode(goApiKey, "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("callTp", "UTF-8")
                    + "=" + URLEncoder.encode("L", "UTF-8")); //"호출할 페이지 타입을 반드시 설정합니다.(L: 목록, D:상세)", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8")
                    + "=" + URLEncoder.encode("1", "UTF-8")); //"기본값 1, 최대 1000 검색 시작위치를 지정할 수 있습니다", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8")
                    + "=" + URLEncoder.encode("100", "UTF-8")); //"출력건수, 기본값 10, 최대 100 까지 가능합니다.", "UTF-8")); /**/
            if (searchId == CHAR_TRGTER_ARRAY||searchId==LIFE_ARRAY) {
                urlBuilder.append("&" + URLEncoder.encode(searchId, "UTF-8") + "=" + URLEncoder.encode(searchValue, "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("trgterIndvdlArray","UTF-8") + "=" + URLEncoder.encode("001", "UTF-8"));

            }
//        선택코드
//            urlBuilder.append("&" + URLEncoder.encode("srchKeyCode","UTF-8") + "=" + URLEncoder.encode("001","UTF-8")); //"001 제목 002 내용 003 제목+내용 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("searchWrd","UTF-8") + "=" + URLEncoder.encode("검색어", "UTF-8")); /**/

//            urlBuilder.append("&" + URLEncoder.encode("lifeArray","UTF-8") + "=" + URLEncoder.encode("001","UTF-8"));//"001, 영유아 002, 아동 003, 청소년 004, 청년 005, 중장년 006, 노년 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("charTrgterArray","UTF-8") + "=" + URLEncoder.encode("004","UTF-8")) ;//"001, 해당없음 002, 여성 003, 임산부 004, 장애 005, 국가유공자등 보훈대상자 006, 실업자 ", "UTF-8")); /**/


//            urlBuilder.append("&" + URLEncoder.encode("obstKiArray","UTF-8") + "=" + URLEncoder.encode("* 대상특성에 장애 클릭시 10, 지체 20, 시각 30, 청각 40, 언어 50, 지적 60, 뇌병변 70, 자폐성 80, 정신 90, 신장 A0, 심장 B0, 호흡기 C0, 간 D0, 안면 E0, 장루 F0, 간질 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("obstLvArray","UTF-8") + "=" + URLEncoder.encode("* 대상특성에 장애 클릭시 1, 1급 2, 2급 3, 3급 4, 4급 5, 5급 6, 6급 ", "UTF-8")); /**/
            /**/
//            urlBuilder.append("&" + URLEncoder.encode("desireArray","UTF-8") + "=" + URLEncoder.encode("0000000, 안전 1000000, 건강 2000000, 일상생활유지 3000000, 가족관계 4000000, 사회적 관계 5000000, 경제 6000000, 교육 7000000, 고용 8000000, 생활환경 9000000, 법률 및 권익보장 A000000, 기타 ", "UTF-8")); /**/

//            ============ URL 문자열 생성


//            ============= URL 객체로 변환
            Log.d("URL", urlBuilder.toString());
            URL url = new URL(urlBuilder.toString());

//            ============= HTTP 프로토콜 객체 변환
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

//            서버에 정보를 요청
//            200 : 요청한 정보에 대한 정상적인 결과를 주겠다.
//            302 : 아짜 줬잖아
//            400 : 잘못된 용청
//            404 : 요청한 주소가 없다. (URL 잘못, ? 빠졌거나)
//            500 : 나(서버)한테 문제 있어서 줄게 없다
            int resCode = conn.getResponseCode();
            Log.d("reCode", ":" + resCode);

//            만약  resCode 가 200이면 데이터를 수신한다
            BufferedReader buffer;
            if (resCode >= 200 && resCode <= 300) {

                InputStreamReader is
                        = new InputStreamReader(conn.getInputStream());
                buffer = new BufferedReader(is);

//            만약 오류가 발생하면
            } else {
                buffer
                        = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream()));
            }

            goDataResult = new StringBuilder();
            String reader = new String();
            while (true) {   // (reader != null) {
                reader = buffer.readLine();
                if (reader == null) break;
                goDataResult.append(reader);
            }
            buffer.close();
            conn.disconnect();

//           =====>> goDataResult에는 xml 형태의 data가 담겨 있다.

//            좀더 편리하게 사용하기 위해 xml을 json으로 변환시킨다.

//        xml to json
            XmlToJson xmlToJson = new XmlToJson
                    .Builder(goDataResult.toString())
                    .build();
            Log.d("JSON", xmlToJson.toString());

//        XmlToJson을 Java의 JSONObject로 변환
            JSONObject jsonObject = xmlToJson.toJson();

//        필요한 데이터만 추출
            try {
                JSONObject wanted = jsonObject.getJSONObject("wantedList");

//       전체 데이터 중에서 servList 배열만 추출
                JSONArray servList = wanted.getJSONArray("servList");


                goDataLists = new ArrayList<GoDataListVO>() ;
//            JSONArray를 List<GoDataListVO> 르 변환
                for (int i = 0; i < servList.length(); i++) {

                    JSONObject item = servList.getJSONObject(i);

//              GoDataListVO(
//                              String servId,
//                              String servNm,
//                              String jurMnofNm,
//                              String jurOrgNm,
//                              String inqNum,
//                              String servDgst,
//                              String servDtlLink,
//                              String svcfrstRegTs)
                    GoDataListVO vo
                            = new GoDataListVO(
                            item.getString("servId"),
                            item.getString("servNm"),
                            item.getString("jurMnofNm"),
                            item.getString("jurOrgNm"),
                            item.getString("inqNum"),
                            item.getString("servDgst"),
                            item.getString("servDtlLink"),
                            item.getString("svcfrstRegTs")
                    );
                    Log.d("VO",vo.toString());
                    goDataLists.add(vo);
//                vo.setInqNum(item.getString("inqNum"));
//                vo.setJurMnofNm(item.getString(""));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   /* @Override
    protected Object doInBackground(Object[] objects) {
//        데이터 가져오기
        this.getData();

//        xml to json
        XmlToJson xmlToJson = new XmlToJson
                .Builder(goDataResult.toString())
                .build();
        Log.d("JSON", xmlToJson.toString());

//        XmlToJson을 Java의 JSONObject로 변환
        JSONObject jsonObject = xmlToJson.toJson();

//        필요한 데이터만 추출
        try {
            JSONObject wanted = jsonObject.getJSONObject("wantedList");

//       전체 데이터 중에서 servList 배열만 추출
            JSONArray servList = wanted.getJSONArray("servList");


            goDataLists = new ArrayList<GoDataListVO>() ;
//            JSONArray를 List<GoDataListVO> 르 변환
            for (int i = 0; i < servList.length(); i++) {

                JSONObject item = servList.getJSONObject(i);

//              GoDataListVO(
//                              String servId,
//                              String servNm,
//                              String jurMnofNm,
//                              String jurOrgNm,
//                              String inqNum,
//                              String servDgst,
//                              String servDtlLink,
//                              String svcfrstRegTs)
                GoDataListVO vo
                        = new GoDataListVO(
                        item.getString("servId"),
                        item.getString("servNm"),
                        item.getString("jurMnofNm"),
                        item.getString("jurOrgNm"),
                        item.getString("inqNum"),
                        item.getString("servDgst"),
                        item.getString("servDtlLink"),
                        item.getString("svcfrstRegTs")
                );
                Log.d("VO",vo.toString());
                goDataLists.add(vo);
//                vo.setInqNum(item.getString("inqNum"));
//                vo.setJurMnofNm(item.getString(""));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return goDataLists;
    }*/

    @Override
    protected List<GoDataListVO> doInBackground(String... strings) {
        goDataLists = new ArrayList<>();
        menu_id =  Integer.valueOf(strings[0]);
        String sv = strings[1];
//        menu_id = integers ;
//        Log.d("String",strings[1]);
//        for(String s : strings) {
//            Log.d("Menu_id_arg",":" + s);
//        }
        Log.d("Menu_id",":" + menu_id);
        switch (menu_id) {

            case R.id.img_preg :
                getData(CHAR_TRGTER_ARRAY,"003",sv);
                break;
            case R.id.img_baby:
                getData(LIFE_ARRAY,"001",sv);
                break;
            case R.id.img_kid :
                getData(LIFE_ARRAY,"002",sv);
                break;
            case R.id.img_search:
                getData(CHAR_TRGTER_ARRAY,"003",sv);
                getData(LIFE_ARRAY,"001",sv);
                getData(LIFE_ARRAY,"002",sv);
                break;

        }


        return goDataLists;
    }

    @Override
    protected void onPostExecute(List<GoDataListVO> goDataListVOS) {
        super.onPostExecute(goDataListVOS);
    }
}
