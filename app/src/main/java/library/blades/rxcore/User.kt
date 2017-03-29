package library.blades.rxcore

/**
 * ClassName: User
 *
 *
 * Author: blades
 *
 *
 * Des: User
 *
 *
 * CreateTime: 2016/10/20 14:15
 *
 *
 * UpdateTime: 2016/10/20 14:15
 *
 *
 * GitHub: https://github.com/AlphaKnife
 */

class User {

    /**
     * id : 1
     * firstname : Amit
     * lastname : Shekhar
     */

    var id: String? = null
    var firstname: String? = null
    var lastname: String? = null

    override fun toString(): String {
        return "User{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}'
    }
}
