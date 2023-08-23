package com.mcjcloud.vscodesplitscreenjb

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.impl.EditorWindow
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl
import com.intellij.openapi.fileEditor.impl.FileEditorOpenOptions
import javax.swing.JSplitPane

class SplitRightAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val editor: FileEditorManagerImpl = FileEditorManager.getInstance(e.project!!) as FileEditorManagerImpl
        val currentWindow = editor.currentWindow ?: return
//        val nextWindow = currentWindow.adjacentEditors[EditorWindow.RelativePosition.RIGHT]
        var nextWindow: EditorWindow? = null
        for (i in 0 until (editor.windows.size)) {
            if (editor.windows[i] == currentWindow && i < editor.windows.size - 1) {
                nextWindow = editor.windows[i + 1]
            }
        }

        val file = editor.currentFile!!

        if (nextWindow != null) {
            currentWindow.closeFile(file)
            editor.openFile(file, nextWindow, FileEditorOpenOptions().withRequestFocus(true))
//            editor.openFileImpl2(nextWindow, file, true)
        } else if (currentWindow.files.size > 1) {
            currentWindow.split(JSplitPane.HORIZONTAL_SPLIT, true, file, true)
            currentWindow.closeFile(file)
        } else {
            return
        }
    }
}