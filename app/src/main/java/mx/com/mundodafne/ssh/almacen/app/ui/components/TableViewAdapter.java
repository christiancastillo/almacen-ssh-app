package mx.com.mundodafne.ssh.almacen.app.ui.components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import mx.com.mundodafne.ssh.almacen.app.R;
import mx.com.mundodafne.ssh.almacen.app.dto.MedicamentoAgregarDTO;

public class TableViewAdapter extends RecyclerView.Adapter<TableViewAdapter.ViewHolder>{
    private List<MedicamentoAgregarDTO> ltMedicamentos;

    public TableViewAdapter(List<MedicamentoAgregarDTO> ltMedicamentos) {
        this.ltMedicamentos = ltMedicamentos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View listItem = li.inflate(R.layout.table_list_item,parent,false);
        ViewHolder holder = new ViewHolder(listItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MedicamentoAgregarDTO medicamentoAgregarDTO = ltMedicamentos.get(position);
        holder.txtViewClaveMedicamento.setText(medicamentoAgregarDTO.getClaveMedicamento());
        holder.txtViewPresentacion.setText(medicamentoAgregarDTO.getUnidadDeMedida());
        holder.txtViewDescripcion.setText(medicamentoAgregarDTO.getDescripcionMedicamento());
        holder.txtViewLoteMedicamento.setText(medicamentoAgregarDTO.getLoteMedicamento());
        holder.txtViewCantidad.setText(String.valueOf(medicamentoAgregarDTO.getCantidad()));
    }

    @Override
    public int getItemCount() {
        return ltMedicamentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtViewClaveMedicamento;
        private TextView txtViewLoteMedicamento;
        private TextView txtViewCantidad;
        private TextView txtViewDescripcion;
        private TextView txtViewPresentacion;
        private LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            txtViewClaveMedicamento = itemView.findViewById(R.id.txtRVClaveMedicamento);
            txtViewLoteMedicamento = itemView.findViewById(R.id.txtRVLote);
            txtViewCantidad = itemView.findViewById(R.id.txtRVQty);
            txtViewDescripcion = itemView.findViewById(R.id.txtRVDescripcion);
            txtViewPresentacion = itemView.findViewById(R.id.txtRVPresentacion);
            linearLayout = itemView.findViewById(R.id.linearLayoutRecyclerView);
        }
    }
}
