package ru.livetex.navigator.client

import org.scalajs.dom.XMLHttpRequest
import org.scalajs.dom.raw._
import org.scalajs.dom
import dom.document

import scala.scalajs.js.JSApp


object ClientApp extends JSApp {

  def question(text: String, callback: (String) => Unit): Unit = {
    val xhr = new XMLHttpRequest()

    xhr.onload = {
      event: Event => callback(xhr.responseText)
    }

    xhr.open("POST", "http://localhost:8080/", async=true)
    xhr.send(text)
  }

  def message(text: String, target: HTMLElement, response: Boolean = true): Unit = {
    val node = document.createElement("div").asInstanceOf[HTMLDivElement]
    node.className = "alert " +
      (if (response) "alert-info" else "alert-success")

    node.innerHTML = text

    target.insertBefore(node, target.firstChild)
  }

  def main(): Unit = {
    val sendButton = document.getElementById("send_button").asInstanceOf[HTMLButtonElement]
    val questionText = document.getElementById("question_text").asInstanceOf[HTMLInputElement]
    val messageList = document.getElementById("message_list").asInstanceOf[HTMLDivElement]

    sendButton.addEventListener("click", {
      event: Event => {
        message(questionText.value, messageList, response=false)

        question(questionText.value, {
          response: String => {
            message("<a target='livetex' href='" + response + "'>Попробуйте тут.</a>", messageList)
            questionText.value = ""
          }
        })
      }
    })
  }
}
