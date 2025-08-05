package com.example.calculator.view

import android.Manifest
import androidx.annotation.RequiresPermission

// HapticUtils.kt


import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.HapticFeedbackConstants
import android.view.View

@RequiresPermission(Manifest.permission.VIBRATE)
fun performHapticAndSound(context: Context, view: View) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(VibrationEffect.createOneShot(70, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator.vibrate(80)
    }

    view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)

    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    audioManager.playSoundEffect(AudioManager.FX_KEY_CLICK)
}
