#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${groupId}.app.bean.Params;
import ${package}.model.Dictionary;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public interface DictionaryService {

    /**
     * 搜索字典
     *
     * @param params 参数
     * @return 返回服务条件的字典
     */
    List<Dictionary> searchDictionaries(Params params);

    /**
     * 保存字典
     *
     * @param dictionary 字典
     */
    void saveDictionary(Dictionary dictionary);

    /**
     * 查找字典
     *
     * @param id 字典ID
     * @return 返回字典
     */
    Dictionary findDictionaryById(Long id);

    /**
     * 更新字典
     *
     * @param dictionary 字典
     */
    void updateDictionary(Dictionary dictionary);

    /**
     * 删除字典
     *
     * @param id 字典ID
     */
    void deleteDictionaryById(Long id);

    /**
     * 校验是否存在字典
     *
     * @param type 字典类型
     * @param code 字典代码
     * @return
     */
    boolean existsDictionary(String type, String code);

    /**
     * 查找指定类型的字典
     *
     * @param type 字典类型
     * @return 返回符合条件的字典
     */
    List<Dictionary> findDictionariesByType(String type);

    /**
     * 批量删除
     *
     * @param ids 字典ID
     */
    void deleteDictionaries(String ids);

}
