package com.etoak.crawl.util;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;

import javax.swing.text.Document;

public class ExportPDF {
//    public void contractPdfDownload(String fileName,String contractContent,HttpServletRequest request,HttpServletResponse response) throws Exception{
//        fileName = getFileName(request, fileName);
//
//        boolean contains = contractContent.contains("<br>");
//        contractContent=contractContent.replace("<br>", "<p style='height:40px;'></p>");
//
//
//
//        String body = contractContent.replace("&nbsp;", "&#160;");
//        body = "<html><head></head><body style=\"font-family:'SimSun';\">"
//                + body + "</body></html>";
//
//        Document document = null;
//        document = DocumentHelper.parseText(body);
//
//        OutputFormat format = OutputFormat.createPrettyPrint();
//        format.setEncoding("utf-8");
//        StringWriter writer = new StringWriter();
//
//        HTMLWriter htmlWriter = new HTMLWriter(writer, format);
//
//        htmlWriter.write(document);
//        htmlWriter.close();
//
//        StringReader contentReader = new StringReader(writer.toString());
//        InputSource source = new InputSource(contentReader);
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder documentBuilder;
//        ServletOutputStream sos = null;
//        try {
//            documentBuilder = factory.newDocumentBuilder();
//            org.w3c.dom.Document xhtmlContent = documentBuilder.parse(source);
//            ITextRenderer renderer = new ITextRenderer();
//            ITextFontResolver fontResolver = renderer.getFontResolver();
//
//            String path = FacesUtil.getRealPath("SIMSUN.TTC",request);
//            fontResolver.addFont(path,
//                    BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            renderer.setDocument(xhtmlContent, "");
//            renderer.layout();
//            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf" + "\"");
//            response.setContentType("application/pdf;charset=UTF-8");
//            sos = response.getOutputStream();
//            renderer.createPDF(sos);
//            renderer.finishPDF();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (com.lowagie.text.DocumentException e) {
//            e.printStackTrace();
//        } finally {
//            IOUtils.closeQuietly(sos);
//        }
//    }
}
