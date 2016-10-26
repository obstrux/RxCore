package library.blades.rxcore;

import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import library.blades.rxcore.base.BaseActivity;
import library.rxlibrary.widget.BaseRecAdapter;
import library.rxlibrary.widget.BaseViewHolder;

public class MainActivity extends BaseActivity {

    // Content View Elements

    private android.support.v7.widget.AppCompatButton btn;
    private android.support.v7.widget.RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private BaseRecAdapter<String> adapter;

    private List<String> datas = new ArrayList<>();

    // End Of Content View Elements

    private void bindViews() {
        btn = (android.support.v7.widget.AppCompatButton) findViewById(R.id.btn);
        recView = (android.support.v7.widget.RecyclerView) findViewById(R.id.recView);

        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new BaseRecAdapter<String>(this) {
            @Override
            public int getItemLayoutId(int viewType) {
                return android.R.layout.activity_list_item;
            }

            @Override
            public void bindData(BaseViewHolder holder, int position, String item) {
                holder.setText(android.R.id.text1, item);
                ImageView icon = holder.getView(android.R.id.icon);
                icon.setImageResource(android.R.drawable.ic_menu_call);
            }
        };

        for (int i = 0; i < 20; i++) {
            datas.add("testing " + i);
        }


        recView.setAdapter(adapter);
        adapter.setList(datas);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datas.add("12");
                datas.remove(10);
                datas.remove(15);


                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyCallback(adapter.getData(), datas), true);
                adapter.setDiffer(datas);

                diffResult.dispatchUpdatesTo(adapter);

            }
        });

    }

}
