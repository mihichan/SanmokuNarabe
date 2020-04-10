package com.hpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainGameActivity extends AppCompatActivity {
    private int count = 0; //現在何手目か。
    private int endFlg; //勝負判定変数　0は試合中、1は終了
    Button[] buttons = new Button[9]; //各ボタンの配列
    int[] _btsan = new int[9];  //各ボタン配列内の〇×判定　1の時〇、2の時×を表示させる
    int cpuMode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanmoku);

        buttons[0] = findViewById(R.id.bt0);
        buttons[1] = findViewById(R.id.bt1);
        buttons[2] = findViewById(R.id.bt2);
        buttons[3] = findViewById(R.id.bt3);
        buttons[4] = findViewById(R.id.bt4);
        buttons[5] = findViewById(R.id.bt5);
        buttons[6] = findViewById(R.id.bt6);
        buttons[7] = findViewById(R.id.bt7);
        buttons[8] = findViewById(R.id.bt8);

        ButtonListener listener = new ButtonListener();

        buttons[0].setOnClickListener(listener);
        buttons[1].setOnClickListener(listener);
        buttons[2].setOnClickListener(listener);
        buttons[3].setOnClickListener(listener);
        buttons[4].setOnClickListener(listener);
        buttons[5].setOnClickListener(listener);
        buttons[6].setOnClickListener(listener);
        buttons[7].setOnClickListener(listener);
        buttons[8].setOnClickListener(listener);
    }

    private class ButtonListener implements View.OnClickListener{
        //ゲームの解説文を表示するTextViewのフィールド宣言
        TextView tvguide = findViewById(R.id.tvGuide);

        //各ボタンをタップした時の処理
        @Override
        public void onClick(View v) {
            //勝敗が着いている時、全てのボタンを操作不可にする処理
            if(endFlg > 0){
                return;
            }

            int id = v.getId();

            //各ボタンに〇か×を表示させる処理　_btsan[]が1になった時〇、2になった時×を表示
            switch (id){
                case R.id.bt0:
                    if(count % 2 != 0){
                        _btsan[0] = 1;
                    }else {
                        _btsan[0] = 2;
                    }
                    break;

                case R.id.bt1:
                    if(count % 2 != 0){
                        _btsan[1] = 1;
                    }else {
                        _btsan[1] = 2;
                    }
                    break;

                case R.id.bt2:
                    if(count % 2 != 0){
                        _btsan[2] = 1;
                    }else {
                        _btsan[2] = 2;
                    }
                    break;

                case R.id.bt3:
                    if(count % 2 != 0){
                        _btsan[3] = 1;
                    }else {
                        _btsan[3] = 2;
                    }
                    break;

                case R.id.bt4:
                    if(count % 2 != 0){
                        _btsan[4] = 1;
                    }else {
                        _btsan[4] = 2;
                    }
                    break;

                case R.id.bt5:
                    if(count % 2 != 0){
                        _btsan[5] = 1;
                    }else {
                        _btsan[5] = 2;
                    }
                    break;

                case R.id.bt6:
                    if(count % 2 != 0){
                        _btsan[6] = 1;
                    }else {
                        _btsan[6] = 2;
                    }
                    break;

                case R.id.bt7:
                    if(count % 2 != 0){
                        _btsan[7] = 1;
                    }else {
                        _btsan[7]= 2;
                    }
                    break;

                case R.id.bt8:
                    if(count % 2 != 0){
                        _btsan[8] = 1;
                    }else {
                        _btsan[8]= 2;
                    }
                    break;
            }

            //何手目かをcountで計測する。countの数値によってボタンに表示される〇×、及びtvguideの表記が変化
            count++;
            if(count % 2 != 0){
                ((Button) v).setText("○");
                ((Button) v).setEnabled(false);
                tvguide.setText("×の手番です");
            } else {
                ((Button) v).setText("×");
                ((Button) v).setEnabled(false);
                tvguide.setText("〇の手番です");
            }

            //勝利判定メソッドで得た変数hによって勝利判定が分岐。また、countが9になるまでに勝敗がつかなければ引き分け
            if(hantei()  > 0){
                endFlg = 1;
                if(count % 2 != 0){
                    tvguide.setText("〇の勝ちです");

                }else {
                    tvguide.setText("×の勝ちです");
                }
            }
            if(count == 9){
                if(endFlg == 0) {
                    tvguide.setText("引き分けです");
                }
            }

        }

        //勝利判定メソッド。勝利条件を満たした時、判定変数hを1として返す
        private int hantei(){
            int h = 0;
            if((_btsan[0] == _btsan[1]) && (_btsan[1] == _btsan[2]) && (_btsan[0] > 0)){
                h = 1;
            }else if((_btsan[3] == _btsan[4]) && (_btsan[4] == _btsan[5]) && (_btsan[3] > 0)){
                h = 1;
            }else if((_btsan[6] == _btsan[7]) && (_btsan[7] == _btsan[8]) && (_btsan[6] > 0)){
                h = 1;
            }else if((_btsan[0] == _btsan[3]) && (_btsan[3] == _btsan[6]) && (_btsan[0] > 0)){
                h = 1;
            }else if((_btsan[1] == _btsan[4]) && (_btsan[4] == _btsan[7]) && (_btsan[1] > 0)){
                h = 1;
            }else if((_btsan[2] == _btsan[5]) && (_btsan[5] == _btsan[8]) && (_btsan[2] > 0)){
                h = 1;
            }else if((_btsan[0] == _btsan[4]) && (_btsan[4] == _btsan[8]) && (_btsan[0] > 0)){
                h = 1;
            }else if((_btsan[2] == _btsan[4]) && (_btsan[4] == _btsan[6]) && (_btsan[2] > 0)){
                h = 1;
            }
            return h;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options_reset,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView tvGuide = findViewById(R.id.tvGuide);
        int itemID = item.getItemId();

        switch (itemID){
            //全ての変数を初期化させるリセットボタン
            case R.id.optionsReset:
                tvGuide.setText("最初は〇の手番です");
                count = 0;
                endFlg = 0;
                for(int i= 0;i < buttons.length;i++){
                    buttons[i].setText("");
                    buttons[i].setEnabled(true);
                    _btsan[i] = 0;
                }
                break;

            //ゲームを継続。つまりなにもしない
            case R.id.optionsRestart:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}