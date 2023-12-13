# Likit-client
Likit-client is a [Likit](https://github.com/CorrectRoadH/Likit) client library for Java.

# Usage
## Maven
```xml
<dependency>
    <groupId>io.github.lxihaaa</groupId>
    <artifactId>likit-client</artifactId>
    <version>1.0.0-RELEASE</version>
</dependency>
```

## Gradle
```xml
implementation group: 'io.github.lxihaaa', name: 'likit-client', version: '1.0.0-RELEASE'
```

## Configuration
add configuration to application.yml
```yaml
likit:
  server:
    host: localhost
    port: 4778
    tls: false
```

if you deploy likit in zeabur. you should set tls to true.

```yaml
likit:
  server:
    host: likit-grpc.zeabur.app
    port: 443
    tls: true
```

## Code
A Comment Like Example
```java
@RestController
@RequestMapping("/comment")
public class VoteController {

    @Autowired
    private LikitService likitService;

    private final String businessId = "COMMENT_LIKE";
    
    @GetMapping("/vote")
    public String Vote(){
        // get userId from JWT, Cookie or any other way
        String userId = ....
        
        // messageId is the id of the thing that be voted
        String messageId = ...
        
        long count = likitService.vote(businessId, messageId, userId);
        return count;
    }

    @GetMapping("/unvote")
    public String UnVote(){
        // get userId from JWT, Cookie or any other way
        String userId = ....

        // messageId is the id of the thing that be voted
        String messageId = ...

        long count = likitService.unvote(businessId, messageId, userId);
        return count;
    }

    @GetMapping("/listComment")
    public String count(){
        // get userId from JWT, Cookie or any other way
        String userId = ....


        Comment[] comments = commentService.getComment(...);
        for (Comment comment : comments) {
            comment.setIsVote(likitService.getIsVote(businessId, comment.getId(), userId));
            comment.setVoteCount(likitService.getVoteCount(businessId, comment.getId()));
        }
        
        return comments;
    }


}
```
