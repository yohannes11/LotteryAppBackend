package et.com.Lottery.dto.responseData;


import et.com.Lottery.dto.requestData.RegisterUserIn;
import et.com.Lottery.dto.restData.Status;

public class RegisterUserOut {
    private Long id;
    private RegisterUserIn registerUserIn;
    private Status status;

    public RegisterUserOut() {
    }

    public RegisterUserOut(Long id, RegisterUserIn registerUserIn, Status status) {
        this.id = id;
        this.registerUserIn = registerUserIn;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegisterUserIn getRegisterUserIn() {
        return registerUserIn;
    }

    public void setRegisterUserIn(RegisterUserIn registerUserIn) {
        this.registerUserIn = registerUserIn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
