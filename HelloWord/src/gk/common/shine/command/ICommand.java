package gk.common.shine.command;

@FunctionalInterface
public interface ICommand {

    /**
     * 执行动作
     */
    void execute();

}