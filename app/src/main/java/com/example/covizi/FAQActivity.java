package com.example.covizi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<FAQ> faqList;
    private ImageView FAQ_backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        recyclerView = findViewById(R.id.faq_recyclerview);
        FAQ_backbtn = findViewById(R.id.faq_backbtn);

        FAQ_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initData();
        setRecyclerView();

    }

    private void initData() {
        faqList = new ArrayList<>();

        faqList.add(new FAQ("1. What is COVizi?","COVizi is an application developed by the Government of Malaysia to assist in managing the COVID-19 outbreaks in the country. It allows users to perform health self-assessment on themselves and their families. The users can also monitor their health progress throughout the COVID-19 outbreak. In addition, MySejahtera enables Ministry of Health (MOH) to monitor users' health condition and take immediate actions in providing the treatments required."));
        faqList.add(new FAQ("2. Who can use COVizi?","COVizi is used by Malaysians and residents of Malaysia."));
        faqList.add(new FAQ("3. How do I register in COVizi?",
                "To register, you need to follow these steps:\n" +
                "\n" +
                "Step 1: Download and install MySejahtera from the Gallery of Malaysian Government Mobile Applications (GAMMA), Apple AppStore, Google Play Store or Huawei AppGallery."));
        faqList.add(new FAQ("4. Where to download COVizi?","COVizi in available in Galeri Aplikasi Mudah Alih Kerajaan Malaysia (GAMMA), Apple App Store, Google Play Store and Huawei AppGallery."));
        faqList.add(new FAQ("5. Is my information secured?","COVizi is owned and operated by the Government of Malaysia. It is administrated by MOH and assisted by NSC and MAMPU. The Government assures that your personal information will only be used for the purpose of managing and mitigating COVID-19 outbreak. It will not be shared to any other party."));
        faqList.add(new FAQ("6. If I am identified as a COVID-19 patient, will my identity be protected?","YES, the information of all COVID-19 patients is protected under the confidentiality of medical records."));
        faqList.add(new FAQ("7. How will the information help to managing the outbreak of Covid-19 in Malaysia?","Essentially, your information will be used by the MOH to help them plan their resources and actions that they need to take efficiently. So please be honest when you submit your information. Honesty will help flatten the curve."));
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(false);
        FAQAdapter faqAdapter = new FAQAdapter(faqList);
        recyclerView.setAdapter(faqAdapter);

    }
}