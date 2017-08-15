import groovy.transform.Field

class FsbClassifier {

  //Includes all the bugs that are bundle with FindBugs by default

  static findBugsPatterns = ["XSS_REQUEST_PARAMETER_TO_SEND_ERROR",
                      "XSS_REQUEST_PARAMETER_TO_SERVLET_WRITER",
                      "HRS_REQUEST_PARAMETER_TO_HTTP_HEADER",
                      "HRS_REQUEST_PARAMETER_TO_COOKIE",
                      "DMI_CONSTANT_DB_PASSWORD",
                      "DMI_EMPTY_DB_PASSWORD",
                      "SQL_NONCONSTANT_STRING_PASSED_TO_EXECUTE",
                      "SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING"
  ]

  //Informational stuff that will interest Security Reviewer but will annoys the developers.
  static informationnalPatterns = ["SERVLET_PARAMETER",
                            "SERVLET_CONTENT_TYPE",
                            "SERVLET_SERVER_NAME",
                            "SERVLET_SESSION_ID",
                            "SERVLET_QUERY_STRING",
                            "SERVLET_HEADER",
                            "SERVLET_HEADER_REFERER",
                            "SERVLET_HEADER_USER_AGENT",
                            "COOKIE_USAGE",
                            "WEAK_FILENAMEUTILS",
                            "JAXWS_ENDPOINT",
                            "JAXRS_ENDPOINT",
                            "TAPESTRY_ENDPOINT",
                            "WICKET_ENDPOINT",
                            "FILE_UPLOAD_FILENAME",
                            "STRUTS1_ENDPOINT",
                            "STRUTS2_ENDPOINT",
                            "SPRING_ENDPOINT",
                            "HTTP_RESPONSE_SPLITTING",
                            "CRLF_INJECTION_LOGS",
                            "EXTERNAL_CONFIG_CONTROL",
                            "STRUTS_FORM_VALIDATION",
                            "ESAPI_ENCRYPTOR",
                            "ANDROID_BROADCAST",
                            "ANDROID_GEOLOCATION",
                            "ANDROID_WEB_VIEW_JAVASCRIPT",
                            "ANDROID_WEB_VIEW_JAVASCRIPT_INTERFACE"]

  //All the cryptography related bugs. Usually with issues related to confidentiality or integrity of data in transit.
  static cryptoBugs = [
          "WEAK_TRUST_MANAGER",
          "WEAK_HOSTNAME_VERIFIER",
          //"WEAK_MESSAGE_DIGEST", //Deprecated
          "WEAK_MESSAGE_DIGEST_MD5",
          "WEAK_MESSAGE_DIGEST_SHA1",
          "CUSTOM_MESSAGE_DIGEST",
          "HAZELCAST_SYMMETRIC_ENCRYPTION",
          "NULL_CIPHER",
          "UNENCRYPTED_SOCKET",
          "DES_USAGE",
          "RSA_NO_PADDING",
          "RSA_KEY_SIZE",
          "BLOWFISH_KEY_SIZE",
          "STATIC_IV",
          "ECB_MODE",
          "PADDING_ORACLE",
          "CIPHER_INTEGRITY"
  ]

  static majorBugsAuditOnly = [ //Mostly due to their high false-positive rate
          "TRUST_BOUNDARY_VIOLATION"
  ]

  //Important bugs but that have lower chance to get full compromise of system (see critical).
  static majorBugs = [
          "PREDICTABLE_RANDOM",
          "PATH_TRAVERSAL_IN",
          "PATH_TRAVERSAL_OUT",
          "REDOS",
          "BAD_HEXA_CONVERSION",
          "HARD_CODE_PASSWORD",
          "HARD_CODE_KEY",
          "XSS_REQUEST_WRAPPER",
          "UNVALIDATED_REDIRECT",
          "ANDROID_EXTERNAL_FILE_ACCESS",
          "ANDROID_WORLD_WRITABLE",
          "INSECURE_COOKIE",
          "HTTPONLY_COOKIE",
          "TRUST_BOUNDARY_VIOLATION",
          "XSS_SERVLET",
          "SPRING_CSRF_PROTECTION_DISABLED",
          "SPRING_CSRF_UNRESTRICTED_REQUEST_MAPPING"
  ]

  static criticalBugs = [ //RCE or powerful function
          "COMMAND_INJECTION",
          "XXE_SAXPARSER",
          "XXE_XMLREADER",
          "XXE_DOCUMENT",
          "SQL_INJECTION_HIBERNATE",
          "SQL_INJECTION_JDO",
          "SQL_INJECTION_JPA",
          "LDAP_INJECTION",
          "XPATH_INJECTION",
          "XML_DECODER",
          "SCRIPT_ENGINE_INJECTION",
          "SPEL_INJECTION",
          "SQL_INJECTION_SPRING_JDBC",
          "SQL_INJECTION_JDBC",
          "EL_INJECTION",
          "SEAM_LOG_INJECTION",
          "OBJECT_DESERIALIZATION",
          "MALICIOUS_XSLT"
  ]

  static majorJspBugs = ["XSS_REQUEST_PARAMETER_TO_JSP_WRITER",
          "XSS_JSP_PRINT", "JSP_JSTL_OUT"]

  static //RCE from JSP specific functions (taglibs)
  criticalJspBugs = ["JSP_INCLUDE","JSP_SPRING_EVAL","JSP_XSLT"]

  static exclusions = ['CUSTOM_INJECTION']

  static deprecatedRules = []

  static String getPriorityFromType(String type) {
    //FSB Specific
    if (type in criticalBugs || type in criticalJspBugs) return "CRITICAL";
    if (type in majorBugs || type in cryptoBugs || type in majorJspBugs) return "MAJOR";
    if (type in informationnalPatterns) return "INFO"

    //println("Unknown priority for "+type)
    return null
  }

}