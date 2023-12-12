# Likit-client
Likit-client is a client library for Java.

# Usage
## maven
```xml
<dependency>
    <groupId>io.github.lxihaaa</groupId>
    <artifactId>likit-client</artifactId>
    <version>1.3-SNAPSHOT</version>
</dependency>
```

## gradle
```xml
...
```

A Comment Like Example
```java
@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private LikitService likitService;

    @GetMapping("/vote")
    public String Vote(){
        long count = likitService.vote("BTYPE","MESSAGE","LXY");
        return "success:" + count;
    }

    @GetMapping("/unvote")
    public String UnVote(){
        long count = likitService.unvote("BTYPE","MESSAGE","LXY");
        return "success:" + count;
    }

    @GetMapping("/count")
    public String count(){
        long count = likitService.getVoteCount("BTYPE", "MESSAGE");
        return "success:" + count;
    }


}
```
