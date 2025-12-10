package eus.ainhizearrese.retrofit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.bumptech.glide.Glide;

import java.util.List;

import eus.ainhizearrese.retrofit.databinding.FragmentOnepieceBinding;
import eus.ainhizearrese.retrofit.databinding.ViewholderContentBinding;

public class OnepieceFragment extends Fragment {
    private FragmentOnepieceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentOnepieceBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OnepieceViewModel onepieceViewModel = new ViewModelProvider(this).get(OnepieceViewModel.class);

        binding.text.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) { return false; }

            @Override
            public boolean onQueryTextChange(String s) {
                onepieceViewModel.search(s);
                return false;
            }
        });
        ContentsAdapter contentsAdapter = new ContentsAdapter();
        binding.recyclerviewContents.setAdapter(contentsAdapter);

        onepieceViewModel.responseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<OnePiece.Response>() {
            @Override
            public void onChanged(OnePiece.Response response) {
                // to control the null pointer on results
                if (response != null && response.results != null) {
                    for (OnePiece.Content content : response.results) {
                        Log.d("ApiReplay", content.name + ", " + content.description + ", " + content.type + ", " + content.filename);
                    }
                } else {
                    Log.d("ApiReplay", "No results found");
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