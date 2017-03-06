package br.com.kakobotasso.restaurantesfiap.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.kakobotasso.restaurantesfiap.R;
import br.com.kakobotasso.restaurantesfiap.models.Restaurante;

public class RestaurantesAdapter extends RecyclerView.Adapter{
    private List<Restaurante> restaurantes;
    private Context context;

    public RestaurantesAdapter(Context context, List<Restaurante> restaurantes) {
        this.context = context;
        this.restaurantes = restaurantes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurante, parent, false);
        view.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) context);
        RestauranteViewHolder holder = new RestauranteViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RestauranteViewHolder holder = (RestauranteViewHolder) viewHolder;
        Restaurante restaurante = restaurantes.get(position);

        holder.nomeRestaurante.setText(restaurante.getNome());
        holder.pedidoRestaurante.setText(restaurante.getPedido());
        holder.opiniaoRestaurante.setText(restaurante.getOpiniao());
    }

    @Override
    public int getItemCount() {
        return restaurantes.size();
    }

    public class RestauranteViewHolder extends RecyclerView.ViewHolder {
        final TextView nomeRestaurante;
        final TextView pedidoRestaurante;
        final TextView opiniaoRestaurante;

        public RestauranteViewHolder(View itemView) {
            super(itemView);
            nomeRestaurante = (TextView) itemView.findViewById(R.id.item_nome_restaurante);
            pedidoRestaurante = (TextView) itemView.findViewById(R.id.item_pedido_restaurante);
            opiniaoRestaurante = (TextView) itemView.findViewById(R.id.item_opiniao_restaurante);
        }
    }
}
