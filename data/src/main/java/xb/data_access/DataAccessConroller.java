package xb.data_access;

import bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

@Controller
@Slf4j
public class DataAccessConroller {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final Gson gson = new Gson();

    public static void main(String[] args) {

    }

    public static String getFileFromPath(){
        return "success";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/redirect")
    public String redirect(){
        return "redirect";
    }

    //region 文件上传
    /**
     *  in spring framework
     *  To enable multipart handling, you need to declare a MultipartResolver bean in your DispatcherServlet Spring configuration with a name of multipartResolver
     *  in spring boot
     *  just use @RequestParam-annotated parameter of type MultipartFile
     *  or List<MultipartFile> to resolve many files with same request name declared in  @RequestParam,like @RequestParam("file") List<MultipartFile> file
     *  or Map<String, MultipartFile> or MultiValueMap<String, MultipartFile>.like  @RequestParam Map<String, MultipartFile> map
     */
    @RequestMapping("/sampleFileUpload")
    @ResponseBody
    public String sampleFileUpload(@RequestParam String name ,@RequestParam("file") MultipartFile file) throws IOException {
        log.info("hello "+name);
        if(file!=null && !file.isEmpty()){
            byte[] bytes = file.getBytes();
            return "upload success";
        }
        return "upload failed!";
    }

    @PostMapping("/sampleFileUpload2")
    @ResponseBody
    public String fileUpload(@RequestParam("name") String name,
                             @RequestParam Map<String, MultipartFile> map) throws IOException {
        log.info("name aaais "+name );
        return "uploadSuccess";
    }

    @PostMapping("/complexFileUpload")
    @ResponseBody
    public String complexFileUpload(User myForm) throws IOException {
        String name = myForm.getName();
        MultipartFile file = myForm.getFile();
        log.info("hello "+name);
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            return "upload success";
        }
        return "upload failed!";
    }
    //endregion

    //region 文件处理
    @PostMapping("/fileProcess")
    @ResponseBody
    public String fileProcess(@RequestParam MultipartFile file) throws Exception {
        log.info("file process start");
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        log.info("file extension is "+extension);
        //List<List<Object>> list = readExcel(file.getInputStream());
        handlerWordMultipartFile(file);
        log.info("file process end");
        return "success";
    }
    //endregion

    //region poi处理excel
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0");// 格式化 number为整

    private static final DecimalFormat DECIMAL_FORMAT_PERCENT = new DecimalFormat("##.00%");//格式化分比格式，后面不足2位的用0补齐

//	private static final DecimalFormat df_per_ = new DecimalFormat("0.00%");//格式化分比格式，后面不足2位的用0补齐,比如0.00,%0.01%

//	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); // 格式化日期字符串

    private static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance("yyyy/MM/dd");

    private static final DecimalFormat DECIMAL_FORMAT_NUMBER  = new DecimalFormat("0.00E000"); //格式化科学计数器

    private static final Pattern POINTS_PATTERN = Pattern.compile("0.0+_*[^/s]+"); //小数匹配
    /**
     * 对外提供读取excel 的方法
     * @param file
     * @return
     * @throws IOException
     */
    public static List<List<Object>> readExcel(MultipartFile file) throws IOException {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        if(Objects.equals("xls", extension) || Objects.equals("xlsx", extension)) {
            return readExcel(file.getInputStream());
        } else {
            throw new IOException("不支持的文件类型");
        }
    }
    /**
     * 对外提供读取excel 的方法
     * @param file
     * @param cls
     * @return
     * @throws IOException
     */
    public static <T> List<T> readExcel(MultipartFile file, Class<T> cls) throws IOException {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        if(Objects.equals("xls", extension) || Objects.equals("xlsx", extension)) {
            return readExcel(file.getInputStream(), cls);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }
    /**
     * 读取 office excel
     *
     * @return
     * @throws IOException
     */
    private static List<List<Object>> readExcel(InputStream inputStream) throws IOException {
        List<List<Object>> list = new LinkedList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            int sheetsNumber = workbook.getNumberOfSheets();
            for (int n = 0; n < sheetsNumber; n++) {
                Sheet sheet = workbook.getSheetAt(n);
                Object value = null;
                Row row = null;
                Cell cell = null;
                for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) { // 从第二行开始读取
                    row = sheet.getRow(i);
                    if (StringUtils.isEmpty(row)) {
                        continue;
                    }
                    List<Object> linked = new LinkedList<>();
                    for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                        cell = row.getCell(j);
                        if (StringUtils.isEmpty(cell)) {
                            continue;
                        }
                        value = getCellValue(cell);
                        linked.add(value);
                    }
                    list.add(linked);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(inputStream);
        }
        return list;
    }
    /**
     * 获取excel数据 将之转换成bean
     *
     * @param cls
     * @param <T>
     * @return
     */
    private static <T> List<T> readExcel(InputStream inputStream, Class<T> cls) {
        List<T> dataList = new LinkedList<T>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            Map<String, List<Field>> classMap = new HashMap<String, List<Field>>();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                if (annotation != null) {
                    String value = annotation.value();
                    if (!classMap.containsKey(value)) {
                        classMap.put(value, new ArrayList<Field>());
                    }
                    field.setAccessible(true);
                    classMap.get(value).add(field);
                }
            }
            Map<Integer, List<Field>> reflectionMap = new HashMap<Integer, List<Field>>();
            int sheetsNumber = workbook.getNumberOfSheets();
            for (int n = 0; n < sheetsNumber; n++) {
                Sheet sheet = workbook.getSheetAt(n);
                for (int j = sheet.getRow(0).getFirstCellNum(); j < sheet.getRow(0).getLastCellNum(); j++) { //首行提取注解
                    Object cellValue = getCellValue(sheet.getRow(0).getCell(j));
                    if (classMap.containsKey(cellValue)) {
                        reflectionMap.put(j, classMap.get(cellValue));
                    }
                }
                Row row = null;
                Cell cell = null;
                for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                    row = sheet.getRow(i);
                    T t = cls.newInstance();
                    for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                        cell = row.getCell(j);
                        if (reflectionMap.containsKey(j)) {
                            Object cellValue = getCellValue(cell);
                            List<Field> fieldList = reflectionMap.get(j);
                            for (Field field : fieldList) {
                                try {
                                    field.set(t, cellValue);
                                } catch (Exception e) {
                                    //logger.error()
                                }
                            }
                        }
                    }
                    dataList.add(t);
                }
            }
        } catch (Exception e) {
            dataList = null;
        } finally {
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(inputStream);
        }
        return dataList;
    }
    /**
     * 获取excel 单元格数据
     *
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell) {
        Object value = null;
        switch (cell.getCellTypeEnum()) {
            case _NONE:
                break;
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)){ //日期
                    value = FAST_DATE_FORMAT.format(DateUtil.getJavaDate(cell.getNumericCellValue()));//统一转成 yyyy/MM/dd
                } else if("@".equals(cell.getCellStyle().getDataFormatString())
                        || "General".equals(cell.getCellStyle().getDataFormatString())
                        || "0_ ".equals(cell.getCellStyle().getDataFormatString())){
                    //文本  or 常规 or 整型数值
                    value = DECIMAL_FORMAT.format(cell.getNumericCellValue());
                } else if(POINTS_PATTERN.matcher(cell.getCellStyle().getDataFormatString()).matches()){ //正则匹配小数类型
                    value = cell.getNumericCellValue();  //直接显示
                } else if("0.00E+00".equals(cell.getCellStyle().getDataFormatString())){//科学计数
                    value = cell.getNumericCellValue();	//待完善
                    value = DECIMAL_FORMAT_NUMBER.format(value);
                } else if("0.00%".equals(cell.getCellStyle().getDataFormatString())){//百分比
                    value = cell.getNumericCellValue(); //待完善
                    value = DECIMAL_FORMAT_PERCENT.format(value);
                } else if("# ?/?".equals(cell.getCellStyle().getDataFormatString())){//分数
                    value = cell.getNumericCellValue(); ////待完善
                } else { //货币
                    value = cell.getNumericCellValue();
                    value = DecimalFormat.getCurrencyInstance().format(value);
                }
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case BLANK:
                //value = ",";
                break;
            default:
                value = cell.toString();
        }
        return value;
    }
    //endregion

    //region poi处理doc
    public void handlerWordMultipartFile(MultipartFile file) throws Exception  {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();

        //String suffex=fileName.substring(lastIndexOf+1,fileName.length());
        try (InputStream is = file.getInputStream()){
            switch (extension) {
                case "doc":
                    //handlerByDocFile(is);
                    break;
                case "docx":
                    handlerByDocxFile(is);
                    break;
                default:
                    throw new IllegalArgumentException("不能解析的文档类型，请输入正确的word文档类型的文件！");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public void handlerWordFile(File file) throws Exception  {
        String fileName = file.getName();
        int lastIndexOf =fileName .lastIndexOf(".");
        if(lastIndexOf==-1) {
            throw new IllegalArgumentException("当前传入的文件格式不合法！");
        }
        String suffex=fileName.substring(lastIndexOf+1,fileName.length());
        try (InputStream is = new FileInputStream(file)){
            switch (suffex) {
                case "doc":
                    //handlerByDocFile(is);
                    break;
                case "docx":
                    handlerByDocxFile(is);
                    break;
                default:
                    throw new IllegalArgumentException("不能解析的文档类型，请输入正确的word文档类型的文件！");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void handlerByDocxFile(InputStream is) throws IOException, InvalidFormatException {
        XWPFDocument xwpfDocument = new XWPFDocument(is);
        Iterator<IBodyElement> bodyElementsIterator = xwpfDocument.getBodyElementsIterator();
        List<Object> datas=new ArrayList<>();
        while (bodyElementsIterator.hasNext()) {
            IBodyElement bodyElement = bodyElementsIterator.next();
            String content = handlerByBodyType(bodyElement,bodyElement.getPartType());
            datas.add(content);
        }
        xwpfDocument.close();
        is.close();
        printAllDatas(datas);
    }

    public void printAllDatas(Collection<?> datas) {
        System.out.println(datas);
    }

    //开始处理当前的身体元素
    public String handlerByIBodyElement(IBodyElement bodyElement) {
        String content=null;
        //用于处理XWPFParagraph
        if(bodyElement instanceof XWPFParagraph) {
            System.out.println("当前获取的元素类型为：XWPFParagraph");
            content=handlerXWPFParagraphType(bodyElement);
        }
        return content;
    }

    //用于处理当前的XWPFParagraph类型的数据
    public String handlerXWPFParagraphType(IBodyElement bodyElement) {
        XWPFParagraph xwpfParagraph = (XWPFParagraph) bodyElement;
        BodyElementType elementType = xwpfParagraph.getElementType();
        String content = getStringByBodyElementType(xwpfParagraph,elementType);
        System.out.println("当前文本的内容为："+content);
        return content;
    }

    //通过当前的类型和元素进行相对应的处理
    public String getStringByBodyElementType(XWPFParagraph xwpfParagraph, BodyElementType bodyElementType) {
        System.out.println(bodyElementType);//当前测试结果为：PARAGRAPH
        String content="";
        switch (bodyElementType) {
            case CONTENTCONTROL:
                //如果使用的是文本控件
                break;
            case PARAGRAPH:
                //如果是段落的处理结果
                content=xwpfParagraph.getParagraphText();
                break;
            case TABLE:
                //如果当前的的元素部分为表格
                break;

            default:
                break;
        }
        return content;
    }

    //通过身体类型来处理
    public String handlerByBodyType(IBodyElement bodyElement , BodyType partType) {
        System.out.println("当前的BodyType为："+partType);
        String content=null;
        switch (partType) {
            case CONTENTCONTROL:
                break;
            case DOCUMENT:
                content=handlerByIBodyElement(bodyElement);
                break;
            case HEADER:

                break;
            case FOOTER:

                break;
            case FOOTNOTE:

                break;
            case TABLECELL:

                break;
            default:
                throw new IllegalArgumentException("there is no this document type !please check this type!");
        }
        return content;
    }
    //endregion

    //region 表单数据接收
    /**
     * 字符串数组传递
     * 注意参数名称要和前端一致
     */
    @RequestMapping("/simpleArr")
    @ResponseBody
    public String[] simpleArr(@RequestParam String[] data) throws JsonProcessingException {
        log.info(mapper.writeValueAsString(data));
        return data;
    }

    /**
     * 对象数组传递
     */
    @PostMapping("/objArr")
    @ResponseBody
    public String objArr(@RequestParam String user) throws IOException {
        log.info(user);
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, User.class);
        List<User> userList = mapper.readValue(user,javaType);
        for(User u: userList){
            log.info("Using Jackson");
            log.info(mapper.writeValueAsString(u));
        }


        List<User> userListOfGson = new ArrayList<>();
        try {
            userListOfGson = gson.fromJson(user, new TypeToken<List<User>>() {
            }.getType());
        } catch (Exception e) {
            log.error("JSON转换错误,String={}", user);
        }

        for(User u: userListOfGson){
            log.info("Using Gson");
            log.info(mapper.writeValueAsString(u));
        }
        return null;
    }

    /**
     * 复选框数据传递
     */
    @RequestMapping(value = "/checkBox",method = RequestMethod.POST)
    @ResponseBody
    public String checkBox(@RequestParam String[] data) throws JsonProcessingException {
        log.info(mapper.writeValueAsString(data));
        return null;
    }

    /**
     * 嵌套对象的序列化与反序列化
     */
    @PostMapping("/objFromForm")
    public String redirect(@RequestParam String _userDTO) throws IOException {
        log.info("输入字符串是");
        log.info(_userDTO);
        UserDTO userDTO = mapper.readValue(_userDTO,UserDTO.class);
        log.info("数据接收成功");
        log.info(mapper.writeValueAsString(userDTO));
        return "redirect";
    }
    //endregion

    //region 对urlencode进行测试
    @GetMapping("/jiahaoTest")
    public String paramHandler(HttpServletRequest request){
        try {
            String test = URLDecoder.decode("1%2A","UTF-8");
            log.info(test);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info(request.getQueryString());
        log.info(request.getParameter("param"));
        return "";
    }

    @RequestMapping("/encodeTest")
    public String encodeTest(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        String param = "去+2";
        String encode1 = URLEncoder.encode(param,"utf-8");
        String encode2 = URLEncoder.encode(encode1,"utf-8");
        redirectAttributes.addAttribute("param",param);
        String redirectUrl = "redirect:/decodeTest?param="+encode1;
        String redirectUrl2 = "redirect:/decodeTest";
        log.info(request.getCharacterEncoding());
        return redirectUrl;
    }

    @RequestMapping("/redirectEndocde")
    public String redirectEndocde(final String param,@RequestParam("param") String param2, HttpServletRequest request,
                             HttpServletResponse response, RedirectAttributes redirectAttributes){
        log.info(param);
        log.info(param2);
       // request.getParameter()
        redirectAttributes.addAttribute("param",param2);
        return "redirect:/decodeTest";
    }

    @RequestMapping("decodeTest")
    public String decodeTest(@RequestParam("param")String param){
        log.info(param);
        return "123";
    }
    //endregion
}
