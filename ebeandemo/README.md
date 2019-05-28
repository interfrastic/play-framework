1.  Follow
    [these instructions](http://stash.wrs.com/projects/RELEASE/repos/sandbox/browse/windriver-dev-env-deliveryplus/README.md)
    to set up the Vagrant VM.

2.  Set up Git on the guest:

    ```
    ssh -p 2222 michaelk@localhost
    cd /home/michaelk/git
    git clone https://github.com/interfrastic/play-framework.git
    cd play-framework
    ```

3.  Set up Mutagen on the host:

    ```
    mutagen create --ignore-vcs --sync-mode=two-way-resolved ~/git/play-framework michaelk@localhost:2222:/home/michaelk/git/play-framework
    ```

4.  On the guest, follow
    [these instructions](https://www.playframework.com/documentation/2.5.9/NewApplication)
    to create a new vanilla Play Java application; for example:
    
    ```
    activator new ebeandemo play-java
    ```
