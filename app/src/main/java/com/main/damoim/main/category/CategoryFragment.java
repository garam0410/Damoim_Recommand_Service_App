package com.main.damoim.main.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.main.damoim.R;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class CategoryFragment  extends Fragment {

   String category_Code, userId;
   int userId_Num;
   public LinearLayout list, webview;

   //서버 url
    String serverURL;
    String mainClass;

    public WebView categoryWebView;
    WebSettings category_WebView_Settings;
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view =  inflater.inflate(R.layout.fragment_category, container, false);

        userId = getArguments().getString("userId");
        userId_Num = getArguments().getInt("userId_Num");
        serverURL = getArguments().getString("serverURL");
        mainClass = getArguments().getString("mainClass");

        ExpandableListView elv = (ExpandableListView) view.findViewById(R.id.elv);

        final ArrayList<Position> position = getData();

        myAdapter adapter = new myAdapter(view.getContext(), position);
        elv.setAdapter(adapter);

        list = (LinearLayout) view.findViewById(R.id.list);
        webview = (LinearLayout) view.findViewById(R.id.webView);

        //정책 카테고리를 클릭했을 때, URL로 이동하기
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                LinearLayout.LayoutParams list_layout = new LinearLayout.LayoutParams(0,0);
                list.setVisibility(View.INVISIBLE);

                list.setLayoutParams(list_layout);

                categoryWebView = (WebView) view.findViewById(R.id.categoryWebView);

                categoryWebView.setWebViewClient(new WebViewClient());
                category_WebView_Settings = categoryWebView.getSettings();
                category_WebView_Settings.setJavaScriptEnabled(true);
                category_WebView_Settings.setSupportMultipleWindows(false);
                category_WebView_Settings.getJavaScriptCanOpenWindowsAutomatically();
                category_WebView_Settings.setLoadWithOverviewMode(true);
                category_WebView_Settings.setUseWideViewPort(true);
                category_WebView_Settings.setSupportZoom(false);
                category_WebView_Settings.setBuiltInZoomControls(false);
                category_WebView_Settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                category_WebView_Settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
                category_WebView_Settings.setDomStorageEnabled(true);
                category_WebView_Settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
                category_WebView_Settings.setEnableSmoothTransition(true);

                //웹으로 띄울때
                try{
                    if(position.get(groupPosition).toString() == "취업지원" && position.get(groupPosition).parent_category.get(childPosition) == "    - 전체"){   //004001
                        category_Code = "004001";
                        //Toast.makeText(getContext(), category_Code, Toast.LENGTH_SHORT).show();
                       // String result;
                        //Connect_Category_Server category_server = new Connect_Category_Server();
                        //result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "취업지원" && position.get(0).parent_category.get(childPosition) == "    - 교육훈련, 체험, 인턴"){    //004001001

                            category_Code = "004001001";
                        //    String result;
                        //    Connect_Category_Server category_server = new Connect_Category_Server();
                        //    result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "취업지원" && position.get(0).parent_category.get(childPosition) == "    - 중소(중견)기업 취업지원"){   //004001002

                            category_Code = "004001002";
                        //   String result;
                        //   Connect_Category_Server category_server = new Connect_Category_Server();
                        //   result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "취업지원" && position.get(0).parent_category.get(childPosition) == "    - 전문분야 취업지원"){   //004001003
                            category_Code = "004001003";
                        //    String result;
                        //    Connect_Category_Server category_server = new Connect_Category_Server();
                        //    result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "취업지원" && position.get(0).parent_category.get(childPosition) == "    - 해외 진출"){   //004001004
                            category_Code = "004001004";
                        //    String result;
                        //    Connect_Category_Server category_server = new Connect_Category_Server();
                        //    result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "창업지원" && position.get(groupPosition).parent_category.get(childPosition) == "    - 전체"){  //004002
                            category_Code = "004002";
                        //    String result;
                        //   Toast.makeText(getContext(), category_Code, Toast.LENGTH_SHORT).show();
                        //   Connect_Category_Server category_server = new Connect_Category_Server();
                        //    result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "창업지원" && position.get(groupPosition).parent_category.get(childPosition) == "    - R&D 지원"){  //004002001
                            category_Code = "004002001";
                        //   String result;
                        //   Connect_Category_Server category_server = new Connect_Category_Server();
                        //   result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "창업지원" && position.get(groupPosition).parent_category.get(childPosition) == "    - 경영지원"){    //004002002
                            category_Code = "004002002";
                        //   String result;
                        //   Connect_Category_Server category_server = new Connect_Category_Server();
                        //    result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "창업지원" && position.get(1).parent_category.get(childPosition) == "    - 자본금 지원"){  //004002003
                            category_Code = "004002003";
                        //   String result;
                        //   Connect_Category_Server category_server = new Connect_Category_Server();
                        //   result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "주거, 금융" && position.get(2).parent_category.get(childPosition) == "    - 전체"){  //004003
                            category_Code = "004003";
                        //   String result;
                        //   Connect_Category_Server category_server = new Connect_Category_Server();
                        //    result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "주거, 금융" && position.get(2).parent_category.get(childPosition) == "    - 생활비지원 및 금융 혜택"){   //004003001
                            category_Code = "004003001";
                        //   String result;
                        //   Connect_Category_Server category_server = new Connect_Category_Server();
                        //   result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "주거, 금융" && position.get(2).parent_category.get(childPosition) == "    - 주거지원"){    //004003002
                            category_Code = "004003002";
                        //    String result;
                        //    Connect_Category_Server category_server = new Connect_Category_Server();
                        //    result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "주거, 금융" && position.get(2).parent_category.get(childPosition) == "    - 학자금 지원"){  //004003003
                            category_Code = "004003003";
                        //    String result;
                        //    Connect_Category_Server category_server = new Connect_Category_Server();
                        //   result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "생활, 복지" && position.get(3).parent_category.get(childPosition) == "    - 전체"){  //004004
                            category_Code = "004004";
                        //    String result;
                        //    Connect_Category_Server category_server = new Connect_Category_Server();
                        //    result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "생활, 복지" && position.get(3).parent_category.get(childPosition) == "    - 건강"){  //004004001
                            category_Code = "004004001";
                        //     String result;
                        //     Connect_Category_Server category_server = new Connect_Category_Server();
                        //    result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "생활, 복지" && position.get(3).parent_category.get(childPosition) == "    - 문화"){  //004004001
                            category_Code = "004004001";
                        ////     String result;
                        //     Connect_Category_Server category_server = new Connect_Category_Server();
                        //     result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "정책참여" && position.get(4).parent_category.get(childPosition) == "    - 전체"){  //004005
                            category_Code = "004005";
                        //    String result;
                        //    Connect_Category_Server category_server = new Connect_Category_Server();
                        //     result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "정책참여" && position.get(4).parent_category.get(childPosition) == "    - 정책제안"){    //004005001
                            category_Code = "004005001";
                        //    String result;
                        //    Connect_Category_Server category_server = new Connect_Category_Server();
                        //    result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "정책참여" && position.get(4).parent_category.get(childPosition) == "    - 권리보호"){    //004005002
                            category_Code = "004005002";
                        //   String result;
                        //   Connect_Category_Server category_server = new Connect_Category_Server();
                        //   result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "정책참여" && position.get(4).parent_category.get(childPosition) == "    - 지역발전"){    //004005003
                            category_Code = "004005003";
                        //String result;
                        //Connect_Category_Server category_server = new Connect_Category_Server();
                        //result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "코로나19" && position.get(5).parent_category.get(childPosition) == "    - 전체"){  //004006
                            category_Code = "004006";
                            //String result;
                            //Connect_Category_Server category_server = new Connect_Category_Server();
                            //result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "코로나19" && position.get(5).parent_category.get(childPosition) == "    - 기본소득지원"){  //004006001
                            //category_Code = "004006001";
                            //String result;
                            //Connect_Category_Server category_server = new Connect_Category_Server();
                            //result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "코로나19" && position.get(5).parent_category.get(childPosition) == "    - 저소득층지원"){  //004006002
                            //category_Code = "004006002";
                            //String result;
                            //Connect_Category_Server category_server = new Connect_Category_Server();
                            //result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }
                    else if(position.get(groupPosition).toString() == "코로나19" && position.get(5).parent_category.get(childPosition) == "    - 재난피해지원"){  //004006003
                            //category_Code = "004006003";
                           // String result;
                            //Connect_Category_Server category_server = new Connect_Category_Server();
                            //result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "코로나19" && position.get(5).parent_category.get(childPosition) == "    - 소득 및 일자리 보전"){ //004006004
                            category_Code = "004006004";
                            //String result;
                            //Connect_Category_Server category_server = new Connect_Category_Server();
                            //result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "코로나19" && position.get(5).parent_category.get(childPosition) == "    - 기타 인센티브"){ //004006005
                            category_Code = "004006005";
                            //String result;
                            //Connect_Category_Server category_server = new Connect_Category_Server();
                            //result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                    }

                    else if(position.get(groupPosition).toString() == "코로나19" && position.get(5).parent_category.get(childPosition) == "    - 심리지원") {    //004006006
                        category_Code = "004006006";
                        //String result;
                       //Connect_Category_Server category_server = new Connect_Category_Server();
                        //result = category_server.execute(category_Code).get();
                        categoryWebView.loadUrl("http://" + serverURL + ":8181/"+mainClass+"/viewCategory.jsp?categoryCode=" + category_Code + "&userId=" + userId);
                        //http://192.168.13.42:8181/parseJSP/viewSearch.jsp?categoryCode=004006006
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                return false;
            }
        });

        return view;
    }

    private ArrayList<Position> getData() {

        Position category_1 = new Position("취업지원");
        category_1.parent_category.add("    - 전체");
        category_1.parent_category.add("    - 교육훈련, 체험, 인턴");
        category_1.parent_category.add("    - 중소(중견)기업 취업지원");
        category_1.parent_category.add("    - 전문분야 취업지원");
        category_1.parent_category.add("    - 해외 진출");

        Position category_2 = new Position("창업지원");
        category_2.parent_category.add("    - 전체");
        category_2.parent_category.add("    - R&D 지원");
        category_2.parent_category.add("    - 경영지원");
        category_2.parent_category.add("    - 자본금 지원");

        Position category_3 = new Position("주거, 금융");
        category_3.parent_category.add("    - 전체");
        category_3.parent_category.add("    - 생활비지원 및 금융 혜택");
        category_3.parent_category.add("    - 주거지원");
        category_3.parent_category.add("    - 학자금 지원");

        Position category_4 = new Position("생활, 복지");
        category_4.parent_category.add("    - 전체");
        category_4.parent_category.add("    - 건강");
        category_4.parent_category.add("    - 문화");

        Position category_5 = new Position("정책참여");
        category_5.parent_category.add("    - 전체");
        category_5.parent_category.add("    - 정책제안");
        category_5.parent_category.add("    - 권리보호");
        category_5.parent_category.add("    - 지역발전");

        Position category_6 = new Position("코로나19");
        category_6.parent_category.add("    - 전체");
        category_6.parent_category.add("    - 기본소득지원");
        category_6.parent_category.add("    - 저소득층지원");
        category_6.parent_category.add("    - 재난피해지원");
        category_6.parent_category.add("    - 소득 및 일자리 보전");
        category_6.parent_category.add("    - 기타 인센티브");
        category_6.parent_category.add("    - 심리지원");

        ArrayList<Position> allposition = new ArrayList<>();
        allposition.add(category_1);
        allposition.add(category_2);
        allposition.add(category_3);
        allposition.add(category_4);
        allposition.add(category_5);
        allposition.add(category_6);

        return allposition;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
