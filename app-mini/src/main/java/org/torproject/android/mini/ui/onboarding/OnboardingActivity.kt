package org.torproject.android.mini.ui.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro
import org.torproject.android.mini.R
import org.torproject.orbotcore.LocaleHelper.onAttach

class OnboardingActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        with(CustomSlideBigText.newInstance(R.layout.custom_slide_big_text)) {
            setTitle(getString(R.string.mini_onboarding_1))
            setSubTitle(getString(R.string.app_name))
            addSlide(this)
        }

        with(CustomSlideBigText.newInstance(R.layout.custom_slide_big_text)) {
            setTitle(getString(R.string.mini_onboarding_2))
            setSubTitle(getString(R.string.mini_onboarding_2_title))
            addSlide(this)
        }

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(resources.getColor(R.color.dark_green))
        setSeparatorColor(resources.getColor(R.color.panel_background_main))

        // Hide Skip/Done button.
        showSkipButton(false)
        isProgressButtonEnabled = true
    }

    override fun onDonePressed(currentFragment: Fragment) {
        super.onDonePressed(currentFragment)
        // Do something when users tap on Done button.
        finish()
    }

    override fun attachBaseContext(base: Context) = super.attachBaseContext(onAttach(base))
}