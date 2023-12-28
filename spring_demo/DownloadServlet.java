package xb.spring.temp;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int BUFFER_SIZE = 8192;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filePath = "/path/to/output/archive.zip";
        File file = new File(filePath);

        if (file.exists()) {
            long fileSize = file.length();
            String rangeHeader = request.getHeader("Range");

            if (rangeHeader != null) {
                // Range请求
                String[] ranges = rangeHeader.split("=")[1].split("-");
                int start = Integer.parseInt(ranges[0]);
                int end = (ranges.length > 1) ? Integer.parseInt(ranges[1]) : (int) (fileSize - 1);
                int contentLength = end - start + 1;

                response.setHeader("Content-Range", "bytes " + start + "-" + end + "/" + fileSize);
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                response.setContentLength(contentLength);
            } else {
                // 非Range请求
                response.setHeader("Content-Length", String.valueOf(fileSize));
            }
            //response.setHeader("Content-Encoding", "gzip");
            response.setContentType("application/zip");
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
            response.setHeader(headerKey, headerValue);

            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);
                 OutputStream os = response.getOutputStream()) {

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

