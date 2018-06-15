public class FaceChatUser {
private String name;
private String email;
private ArrayList friendList;
private ArrayList blockList;

public FaceChatUser(String inName, String inEmail) {
name = inName;
email = inEmail;
friendList = new ArrayList();
blockList = new ArrayList();
}

public void addFriend(FaceChatUser friend) {
friendList.add(friend);
}

public void blockUser(FaceChatUser blocked) {
blockList.add(blocked);
}

public void postUpdate(String text, Picture pic) {
//not implemented yet 
}

public String getEmail() {
return email;
}

public String getName() {
return name;
}
}


FaceChatUser chadAccount = new FaceChatUser("Chad","chadm@umn.edu");
FaceChatUser goldyAccount = new FaceChatUser("Goldy","goldy@umn.edu");

chadAccount.addFriend(goldyAccount);

System.out.println("User's email is: "+chadsAccount.getEmail());
