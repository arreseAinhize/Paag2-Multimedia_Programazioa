package eus.arreseainhize.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import eus.arreseainhize.recyclerview.databinding.FragmentNewElementBinding;

public class NewElementFragment extends Fragment {

    private FragmentNewElementBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentNewElementBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ElementsViewModel elementosViewModel = new ViewModelProvider(requireActivity()).get(ElementsViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.newElementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.name.getText().toString();
                String description = binding.description.getText().toString();

                // Validar campos obligatorios
                if (name.isEmpty() || description.isEmpty()) {
                    // Mostrar error si los campos están vacíos
                    if (name.isEmpty()) binding.name.setError("Nombre requerido");
                    if (description.isEmpty()) binding.description.setError("Descripción requerida");
                    return;
                }

                elementosViewModel.add(new Element(R.drawable.vuejs_original, name, description));
                navController.popBackStack();
            }
        });
    }
}