package pl.nojkir.weatherapp.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.*
import kotlinx.coroutines.launch
import pl.nojkir.repository.UserPreferencesRepository
import pl.nojkir.weatherapp.R
import pl.nojkir.weatherapp.databinding.ActivityMainBinding.bind
import pl.nojkir.weatherapp.databinding.SettingsFragmentBinding

class SettingsFragment: Fragment(R.layout.settings_fragment){

    private lateinit var userPreferences: UserPreferencesRepository
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SettingsFragmentBinding.bind(view)

        userPreferences = UserPreferencesRepository(requireContext())
        binding.buttonSave.setOnClickListener {
            var checkedButton = binding.radioGroupLanguage.checkedRadioButtonId
            var language  = if (binding.rbEnglish.isChecked) {

                Toast.makeText(requireContext(), "English", Toast.LENGTH_LONG).show()
            }else
                    Toast.makeText(requireContext(), "Polish", Toast.LENGTH_LONG).show()
                }


        binding.buttonSave.setOnClickListener { lifecycleScope.launch {
            var language: String = if (binding.rbEnglish.isChecked) "English" else "Polish"
            userPreferences.saveToDataStore(language)
        } }

        
            }

        }




