package library.blades.rxcore

/**
 * ClassName: ApiUser
 *
 *
 * Author: blades
 *
 *
 * Des: ApiUser
 *
 *
 * CreateTime: 2016/11/4 14:52
 *
 *
 * UpdateTime: 2016/11/4 14:52
 *
 *
 * GitHub: https://github.com/AlphaKnife
 */
class ApiUser {

    var status: String
    var msg: String
    var totalPage: Int = 0
    var results: List<ResultsBean>

    class ResultsBean {
        var id: String? = null
        var title: String? = null
        var creatDept: String? = null
        var creatPerson: String? = null
        var creatTime: String? = null
        /**
         * fileName : gyf_整理工信综管文档.docx
         * fileSize : 134KB
         * id : a4a47721c4134ad889454c6709c6f1a1
         */

        var attachments: List<AttachmentsBean>? = null


        class AttachmentsBean {
            var fileName: String? = null
            var fileSize: String? = null
            var id: String? = null
        }

    }

    override fun toString(): String {
        return "ApiUser{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", totalPage=" + totalPage +
                ", results=" + results +
                '}'
    }
}
