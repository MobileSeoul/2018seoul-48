package com.biz.woojjujju.service;

import android.os.AsyncTask;
import android.util.Log;

import com.biz.woojjujju.config.GoDataKey;
import com.biz.woojjujju.models.ServiceDataListVO;

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

public class AllDataGetterService extends AsyncTask<String,String,List<ServiceDataListVO>> {

    int menu_id = 0 ;

    private String goAPIKey = GoDataKey.GoAPIkey;
    private String goURL = GoDataKey.GoAPIurl;
    private StringBuilder goDataResult;
    private List<ServiceDataListVO> goDataLists ;

    private static String servid ;


//    public void getData() {
    private void getData(String searchValue,String sv) {
        // Java String 변수를 생성하고 계속 이어붙이기를 하는 것은 성능상 문제가 있다
        StringBuilder urlBuilder = new StringBuilder(goURL);

//        필수코드
        try {
            // Service
            urlBuilder.append("?" + URLEncoder.encode("crtiKey", "UTF-8") + "=" + goAPIKey); // URLEncoder.encode(goApiKey, "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("callTp", "UTF-8")  + "=" + URLEncoder.encode("D", "UTF-8")); //"호출할 페이지 타입을 반드시 설정합니다.(L: 목록, D:상세)", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("servId", "UTF-8") + "=" + URLEncoder.encode(searchValue, "UTF-8")); //"기본값 1, 최대 1000 검색 시작위치를 지정할 수 있습니다", "UTF-8")); /**/

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
                JSONObject wanted = jsonObject.getJSONObject("wantedDtl");

//       전체 데이터 중에서 servList 배열만 추출

//                JSONArray inqplHmpgReldList = wanted.getJSONArray("inqplHmpgReldList");


                goDataLists = new ArrayList<ServiceDataListVO>() ;

                ServiceDataListVO vo
                        = new ServiceDataListVO(

                        wanted.getString("servNm"),
                        wanted.getString("servDgst"),
                        wanted.getString("tgtrDtlCn"),
                        wanted.getString("slctCritCn"),
                        wanted.getString("alwServCn")
                );
                try{
                    JSONObject applmetList = wanted.getJSONObject("applmetList");
                    vo.setHmpgDatailLink(applmetList.getString("servSeDetailNm"));
                }catch (Exception e)
                {
                    Log.d("TEST","null");
                }

                try {
                    JSONObject inqplCtadrList  = wanted.getJSONObject("inqplCtadrList");
                    vo.setInqpDatailNm(inqplCtadrList.getString("servSeDetailNm"));
                    vo.setInqpDatailLink(inqplCtadrList.getString("servSeDetailLink"));
                }catch (Exception e)
                {
                    Log.d("TEST","null");
                }

//                for (int i = 0; i < applmetList.length(); i++) {
//
//                    JSONObject item = applmetList.getJSONObject(i);
//
//                }

//                for (int i = 0; i <inqplHmpgReldList.length(); i++) {
//
//                    JSONObject item = applmetList.getJSONObject(i);
//                    vo.setHmpgDatailLink(item.getString("servSeDetailLink"));
//                }
                Log.d("servVO",vo.toString());
                goDataLists.add(vo);
//            JSONArray를 List<GoDataListVO> 르 변환
                /*for (int i = 0; i < servList.length(); i++) {

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
                    ServiceDataListVO vo
                            = new ServiceDataListVO(

                            item.getString("servNm"),
                            item.getString("servDgst"),
                            item.getString("tgtrDtlCn"),
                            item.getString("slctCritCn"),
                            item.getString("alwServCn"),
                            item.getString("servSeDetailNm"),
                            item.getString("inqplCtadrList"),
                            item.getString("servSeDetailLink")
                    );
                    Log.d("VO",vo.toString());
                    goDataLists.add(vo);
//                vo.setInqNum(item.getString("inqNum"));
//                vo.setJurMnofNm(item.getString(""));
                }*/

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


    @Override
    protected List<ServiceDataListVO> doInBackground(String... strings) {
        goDataLists = new ArrayList<>();
        servid = strings[0];
        String sv = strings[1];

        getData(servid,sv);


        return goDataLists;
    }

    @Override
    protected void onPostExecute(List<ServiceDataListVO> goDataListVOS) {
        super.onPostExecute(goDataListVOS);
    }
}
