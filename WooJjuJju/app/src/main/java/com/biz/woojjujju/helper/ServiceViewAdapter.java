package com.biz.woojjujju.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.woojjujju.R;
import com.biz.woojjujju.models.ServiceDataListVO;

import java.util.List;

public class ServiceViewAdapter extends RecyclerView.Adapter {
    List<ServiceDataListVO> list;

    public ServiceViewAdapter(List<ServiceDataListVO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_data,parent,false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ServiceViewHolder goDataViewHolder = (ServiceViewHolder) holder;
        ServiceDataListVO vo = list.get(position);
        goDataViewHolder.servNm.setText(vo.getServNm());
        goDataViewHolder.tgtrDtlCn.setText(vo.getTgtrDtlCn());
        goDataViewHolder.slctCritCn.setText(vo.getSlctCritCn());
        goDataViewHolder.alwServCn.setText(vo.getAlwServCn());


        goDataViewHolder.servSeDetailNm.setText(vo.getHmpgDatailLink());

        goDataViewHolder.ctadrDetailNm.setText(vo.getInqpDatailNm());
        goDataViewHolder.ctadrDetailLink.setText(vo.getInqpDatailLink());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder {


        TextView servNm;
        TextView tgtrDtlCn;// 대상자 상세내용
        TextView slctCritCn;// 선정기준 내용
        TextView alwServCn;//   급여서비스 내용
        TextView servSeDetailNm;// 서비스 이용
        TextView ctadrDetailNm;//   급여서비스 내용
        TextView ctadrDetailLink;// 서비스 이용








        public ServiceViewHolder(View itemView) {
            super(itemView);
            servNm = itemView.findViewById(R.id.item_data_serv_nm);
            tgtrDtlCn = itemView.findViewById(R.id.item_data_tgtr_dtl_cn);
            slctCritCn = itemView.findViewById(R.id.item_data_slct_crit_cn);
            alwServCn = itemView.findViewById(R.id.item_data_alw_serv_cn);
            servSeDetailNm = itemView.findViewById(R.id.item_data_serv_se_detail_nm);

            ctadrDetailNm = itemView.findViewById(R.id.item_data_inqpl_ctadr_nm);
            ctadrDetailLink = itemView.findViewById(R.id.item_data_inqpl_ctadr_link);


        }
    }
}
