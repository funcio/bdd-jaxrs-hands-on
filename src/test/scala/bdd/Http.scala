package bdd

import java.net.{HttpURLConnection, URL}
import util.matching.Regex
import collection.JavaConversions._
import java.io.IOException


case class Response(status:Int, headers:Map[String,Seq[String]],body:String)

object Http {
  val baseUrl = "http://localhost:8080"
  val httpHeaderRegexp:Regex = """^([A-Za-z\-]+):(.+)$""".r


  def send(verb: String, path: String, headersAndBody: String = "\n\n") = {
    val url = {
      if (path.startsWith("http")) {
        new URL(path)
      } else {
        new URL(baseUrl + path)
      }
    }
    val conn = url.openConnection.asInstanceOf[HttpURLConnection]
    val headersAndBodyParts = headersAndBody.split("\n")
    val headers = headersAndBodyParts.takeWhile(httpHeaderRegexp.pattern.matcher(_).matches())
      .map{case httpHeaderRegexp(headerName,headerValue) => headerName->headerValue}.toMap
    val requestBody = headersAndBodyParts.dropWhile(httpHeaderRegexp.pattern.matcher(_).matches()).mkString("\n")
    headers.foreach(h => conn.setRequestProperty(h._1, h._2))
    conn.setRequestMethod(verb)
    if (!requestBody.isEmpty) {
      conn.setDoOutput(true)
      val os = conn.getOutputStream
      os.write(requestBody.getBytes)
      os.flush()
      os.close()
    }
    val responseBody =
    try {
      scala.io.Source.fromInputStream(conn.getInputStream).mkString
    } catch {
      case e:IOException =>
      scala.io.Source.fromInputStream(conn.getErrorStream).mkString
    }
    Response(conn.getResponseCode, conn.getHeaderFields.mapValues(_.toSeq).toMap, responseBody)
  }

}
