# Likit-client
Likit-client is a client library for Java.

# Usage
## maven
```xml
<dependency>
    <groupId>io.github.lxihaaa</groupId>
    <artifactId>likit-client</artifactId>
    <version>1.1-SNAPSHOT</version>
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
    private VoteUseCase voteUseCase;

    @GetMapping("/vote")
    public String Vote(){
        long count = voteUseCase.vote(new VoteDTO("BTYPE","MESSAGE","LXY"));
        return "success:" + count;
    }

    @GetMapping("/unvote")
    public String UnVote(){
        long count = voteUseCase.unvote(new VoteDTO("BTYPE","MESSAGE","LXY"));
        return "success:" + count;
    }

    @GetMapping("/count")
    public String count(){
        long count = voteUseCase.count("BTYPE", "MESSAGE");
        return "success:" + count;
    }

}
```
