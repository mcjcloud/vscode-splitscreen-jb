package com.mcjcloud.vscodesplitscreenjb

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.impl.EditorWindow
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl
import com.intellij.openapi.fileEditor.impl.FileEditorOpenOptions

class SplitLeftAction : AnAction() {
//    override fun actionPerformed(e: AnActionEvent) {
//        val editor: FileEditorManagerImpl = FileEditorManager.getInstance(e.project!!) as FileEditorManagerImpl
//        val currentWindow = editor.currentWindow
//        val prevWindow = currentWindow.adjacentEditors[EditorWindow.RelativePosition.LEFT] ?: return
//
//        val file = editor.currentFile!!
//
//        currentWindow.closeFile(file)
//        editor.openFileImpl2(prevWindow, file, true)
//    }
    override fun actionPerformed(e: AnActionEvent) {
        val editor: FileEditorManagerImpl = FileEditorManager.getInstance(e.project!!) as FileEditorManagerImpl
        val currentWindow = editor.currentWindow
        var prevWindow: EditorWindow? = null
        for (i in 0 until (editor.windows.size)) {
            if (editor.windows[i] == currentWindow && i > 0) {
                prevWindow = editor.windows[i - 1]
            }
        }

        val file = editor.currentFile!!

        currentWindow?.closeFile(file)
        editor.openFile(file, prevWindow, FileEditorOpenOptions().withRequestFocus(true))
        prevWindow?.requestFocus(true)
    }
}