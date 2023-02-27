package mx.com.mundodafne.ssh.almacen.app.ui.components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MedicamentoAgregarDTO medicamentoAgregarDTO = this.ltMedicamentos.get(position);
        int rowPos = holder.getAdapterPosition();
        if (rowPos == 0) {
            holder.txtViewClaveMedicamento.setText("CLAVE");
            holder.txtViewPresentacion.setText("PRESENTACION");
            holder.txtViewDescripcion.setText("DESC");
            holder.txtViewLoteMedicamento.setText("LOTE");
            holder.txtViewCantidad.setText("QTY");
        } else {
            holder.txtViewClaveMedicamento.setText(medicamentoAgregarDTO.getClaveMedicamento());
            holder.txtViewPresentacion.setText(medicamentoAgregarDTO.getUnidadDeMedida());
            holder.txtViewDescripcion.setMaxWidth(500);
            holder.txtViewDescripcion.setText(medicamentoAgregarDTO.getDescripcionMedicamento());
            holder.txtViewLoteMedicamento.setText(medicamentoAgregarDTO.getLoteMedicamento());
            holder.txtViewCantidad.setText(String.valueOf(medicamentoAgregarDTO.getCantidad()));
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
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