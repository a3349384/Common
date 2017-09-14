package cn.zmy.common.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.zmy.common.widget.checkable.CheckableLinearLayout;

/**
 * Created by zmy on 2017/9/14.
 */

public class TestCheckableWidgetActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_checkable_widget);

        findViewById(R.id.checkableLinearLayout).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((CheckableLinearLayout)v).toggle();
            }
        });
    }
}
