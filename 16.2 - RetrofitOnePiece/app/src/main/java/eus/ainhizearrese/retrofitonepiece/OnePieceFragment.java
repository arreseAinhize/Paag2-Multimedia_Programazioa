package eus.ainhizearrese.retrofitonepiece;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import eus.ainhizearrese.retrofitonepiece.databinding.FragmentOnePieceBinding;
import eus.ainhizearrese.retrofitonepiece.databinding.ViewholderContentBinding;

/**
 * Egindako aldaketak:
 *
 * 1.- Actualizar el "Observer" para observar "OnePiece.Content"
 *
 * 2.- Modificar la logica de "onChange"
 *          * como ahora recibimos un solo objeto Content (o null),
 *          lo envolvemos en una lista (Collections.singletonList(content))
 *          para pasárselo al ContentsAdapter, que espera una lista.
 *
 * 3.- Ajustar la llamada a onepieceViewModel.search(s) para que solo se
 * haga si la cadena no está vacía.Ahora la aplicación debería ser capaz
 * de buscar una fruta por su ID numérico (ej. "1", "2") y mostrar el
 * resultado en el RecyclerView.
 *
 */

public class OnePieceFragment extends Fragment {
    private FragmentOnePieceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentOnePieceBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OnePieceViewModel onepieceViewModel = new ViewModelProvider(this).get(OnePieceViewModel.class);

        binding.text.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) { return false; }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s != null && !s.isEmpty()) {
                    onepieceViewModel.search(s);
                }
                return false;
            }
        });
        ContentsAdapter contentsAdapter = new ContentsAdapter();
        binding.recyclerviewContents.setAdapter(contentsAdapter);

        onepieceViewModel.responseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<OnePiece.Content>() {
            @Override
            public void onChanged(OnePiece.Content content) {
                // to control the null pointer on results
                if (content != null) {
                    Log.d("ApiReplay", content.name + ", " + content.description + ", " + content.type + ", " + content.filename);
                    contentsAdapter.establishContentList(Collections.singletonList(content));
                } else {
                    Log.d("ApiReplay", "No results found");
                    contentsAdapter.establishContentList(Collections.emptyList());
                }
            }
        });

    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {
        ViewholderContentBinding binding;

        public ContentViewHolder (@NonNull ViewholderContentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class ContentsAdapter extends RecyclerView.Adapter<ContentViewHolder>{
        List<OnePiece.Content> contentList;

        @NonNull
        @Override
        public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ContentViewHolder(ViewholderContentBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
            OnePiece.Content content = contentList.get(position);

            holder.binding.fruteName.setText(content.name);
            holder.binding.fruteType.setText(content.type);
            holder.binding.fruteDescription.setText(content.description);
            Glide.with(requireActivity()).load(content.filename).into(holder.binding.fruteImg);
        }

        @Override
        public int getItemCount() {
            return contentList == null ? 0 : contentList.size();
        }

        void establishContentList(List<OnePiece.Content> contentList){
            this.contentList = contentList;
            notifyDataSetChanged();
        }
    }
}
