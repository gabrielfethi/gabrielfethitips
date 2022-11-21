package reportTemplates;

public class ReportLinkVerificationTemplate {

    public static String topHTMLPortion;

    public static String bottomHTMLPortion = "</tbody>\n" +
            "    </table>\n" +
            "</body>\n" +
            "</html>";

    public static StringBuilder dynamicHTMLPortion = new StringBuilder();

    public static void setDynamicHTMLPortion(String testPage, String linkTested, Boolean testFlag) {

        if(testFlag){
            ReportLinkVerificationTemplate.dynamicHTMLPortion.append("<tr>\n" +
                    "                    <td>" + testPage + "</td>\n" +
                    "                    <td>" + linkTested + "</td>\n" +
                    "                    <td>PASS</td>\n" +
                    "                    </tr>");
        }

        if(!testFlag){
            ReportLinkVerificationTemplate.dynamicHTMLPortion.append("<tr class=\"active-row\">\n" +
                    "                    <td>" + testPage + "</td>\n" +
                    "                    <td>" + linkTested + "</td>\n" +
                    "                    <td>FAIL</td>\n" +
                    "                    </tr>");
        }
    }

    public static String setTopHTMLPortion(String dateText) {
        return topHTMLPortion = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style>\n" +
                "        .report-header{\n" +
                "    background-color: #009879;\n" +
                "    color: #ffffff;\n" +
                "    text-align: left;\n" +
                "        }\n" +
                "        .styled-table {\n" +
                "    border-collapse: collapse;\n" +
                "    margin: 25px 0;\n" +
                "    font-size: 0.9em;\n" +
                "    font-family: sans-serif;\n" +
                "    min-width: 400px;\n" +
                "    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);\n" +
                "        }\n" +
                "        .styled-table thead tr {\n" +
                "    background-color: #009879;\n" +
                "    color: #ffffff;\n" +
                "    text-align: left;\n" +
                "        }\n" +
                "        .styled-table th,\n" +
                "        .styled-table td {\n" +
                "    padding: 12px 15px;\n" +
                "        }\n" +
                "        .styled-table tbody tr {\n" +
                "    border-bottom: 1px solid #dddddd;\n" +
                "    }\n" +
                "\n" +
                "    .styled-table tbody tr:nth-of-type(even) {\n" +
                "    background-color: #f3f3f3;\n" +
                "    }\n" +
                "    .styled-table tbody tr:last-of-type {\n" +
                "    border-bottom: 2px solid #009879;\n" +
                "    }\n" +
                "    .styled-table tbody tr.active-row {\n" +
                "    font-weight: bold;\n" +
                "    color: red; \n" +
                "    }   \n" +
                "    </style>\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1 class=\"report-header\">Test Run For Broken Links || Date: " + dateText + "</h1>\n" +
                "    <hr>\n" +
                "    <table class=\"styled-table\">\n" +
                "            <thead>\n" +
                "                <tr>\n" +
                "                    <th>Test Page</th>\n" +
                "                    <th>Link</th>\n" +
                "                    <th>Status</th>\n" +
                "                </tr>\n" +
                "                </thead>\n" +
                "                <tbody>";
    }

    public static String getHtmlCode(String testRunDate){

        setTopHTMLPortion(testRunDate);

        return ReportLinkVerificationTemplate.topHTMLPortion + ReportLinkVerificationTemplate.dynamicHTMLPortion.toString() + ReportLinkVerificationTemplate.bottomHTMLPortion;
    }




}
