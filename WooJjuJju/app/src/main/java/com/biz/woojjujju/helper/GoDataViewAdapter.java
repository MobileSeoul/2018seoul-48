package com.biz.woojjujju.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.woojjujju.R;
import com.biz.woojjujju.models.GoDataListVO;

import java.util.ArrayList;
import java.util.List;

public class GoDataViewAdapter extends RecyclerView.Adapter {
    private List<GoDataListVO> ListVO;
    private OnItemClickListener clickListener;
    public static List<GoDataListVO> getListVO = new ArrayList<>();
    public static String servId;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        clickListener = listener;
    }

    public GoDataViewAdapter(List<GoDataListVO> listVO) {
        ListVO = listVO;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_go_data,parent,false);
        return new GoDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    GoDataViewHolder goDataViewHolder = (GoDataViewHolder) holder;
    GoDataListVO vo = ListVO.get(position);
    goDataViewHolder.servNm.setText(vo.getServNm());

    goDataViewHolder.servDgst.setText(Html.fromHtml(vo.getServDgst()));
    goDataViewHolder.txtBtn.setTag(vo.getServId());
    servId = (String) goDataViewHolder.txtBtn.getTag();
    vo.setServId(servId);
    getListVO.add(vo);
    Log.d("servId", servId);
//    while (true) {
//
//            Log.d("servId", servId);
//            if (servId.equalsIgnoreCase(vo.getServId()))
//                break;
//        }
//    goDataViewHolder.txtBtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            String servId = (String)v.getTag() ;
//            Log.d("id",servId);
//            Intent intent = new Intent(context,ServiceActivity.class);
//            intent.putExtra("SERVID",servId);
//            context.startActivity(intent);
//        }
//    });

        }

    @Override
    public int getItemCount() {
        return ListVO.size();
    }
    class GoDataViewHolder extends RecyclerView.ViewHolder {
//        private String servNm;       //	서비스명
//            private String servDgst;     //	서비스 요약
        TextView servNm;
        TextView servDgst;

        TextView txtBtn;
        public GoDataViewHolder(View itemView) {
            super(itemView);
            servNm = itemView.findViewById(R.id.item_txt_serv_nm);
            servDgst = itemView.findViewById(R.id.item_txt_serv_dgst);
            txtBtn = itemView.findViewById(R.id.txt_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener !=null)
                    {

                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            clickListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
