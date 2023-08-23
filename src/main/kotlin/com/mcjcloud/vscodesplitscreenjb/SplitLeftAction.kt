package com.mcjcloud.vscodesplitscreenjb

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.impl.EditorWindow
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl

class SplitLeftAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val editor: FileEditorManagerImpl = FileEditorManager.getInstance(e.project!!) as FileEditorManagerImpl
        val currentWindow = editor.currentWindow
        val prevWindow = currentWindow.adjacentEditors[EditorWindow.RelativePosition.LEFT] ?: return

        val file = editor.currentFile!!

        currentWindow.closeFile(file)
        editor.openFileImpl2(prevWindow, file, true)
    }
}