package eus.arreseainhize.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import eus.arreseainhize.recyclerview.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private ElementsViewModel elementsViewModel;
    private Element currentElement;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentDetailBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ElementsViewModel elementsViewModel = new ViewModelProvider(requireActivity()).get(ElementsViewModel.class);

        // Configurar el listener UNA SOLA VEZ
        binding.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser && currentElement != null) {
                    elementsViewModel.update(currentElement, rating);
                }
            }
        });

        elementsViewModel.selected().observe(getViewLifecycleOwner(), new Observer<Element>() {
            @Override
            public void onChanged(Element element) {
                currentElement = element; // Actualizar la referencia
                binding.name.setText(element.name);
                binding.description.setText(element.description);
                binding.rating.setRating(element.rating);
            }
        });
    }
}