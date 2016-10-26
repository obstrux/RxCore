package library.blades.rxcore;

/**
 * ClassName: User<p>
 * Author: blades<p>
 * Des: User<p>
 * CreateTime: 2016/10/20 14:15<p>
 * UpdateTime: 2016/10/20 14:15<p>
 * GitHub: https://github.com/AlphaKnife
 */

public class User {

    /**
     * id : 1
     * firstname : Amit
     * lastname : Shekhar
     */

    public String id;
    public String firstname;
    public String lastname;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
