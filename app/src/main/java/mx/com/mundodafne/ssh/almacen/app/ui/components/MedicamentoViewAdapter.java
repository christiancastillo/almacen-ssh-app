package mx.com.mundodafne.ssh.almacen.app.ui.components;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.com.mundodafne.ssh.almacen.app.dto.MedicamentoAgregarDTO;

public class MedicamentoViewAdapter extends RecyclerView.Adapter<MedicamentoViewAdapter.ViewHolder> {
    private MedicamentoAgregarDTO[] arrMedicamentos;

    @NonNull
    @Override
    public MedicamentoViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentoViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    }
