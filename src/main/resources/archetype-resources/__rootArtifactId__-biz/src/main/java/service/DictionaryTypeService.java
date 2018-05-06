#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${groupId}.app.bean.Params;
import ${package}.model.DictionaryType;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public interface DictionaryTypeService {

    /**
     * 搜索字典类型
     *
     * @param params 参数
     * @return 返回符合条件的字典类型
     */
    List<DictionaryType> searchDictionaryTypes(Params params);

    /**
     * 保存字典类型
     *
     * @param dictionaryType 字典类型
     */
    void saveDictionaryType(DictionaryType dictionaryType);

    /**
     * 更新字典类型
     *
     * @param dictionaryType 字典类型
     */
    void updateDictionaryType(DictionaryType dictionaryType);

    /**
     * 查找字典类型
     *
     * @param id 字典类型ID
     * @return 返回字典类型
     */
    DictionaryType findDictionaryTypeById(Long id);

    /**
     * 删除字典类型
     *
     * @param id 字典类型ID
     */
    void deleteDictionaryTypeById(Long id);

    /**
     * 校验是否存在字典类型
     *
     * @param type 字典类型
     * @return
     */
    boolean existsDictionaryType(String type);

    /**
     * 查找所有字典类型
     *
     * @return 返回所有字典类型
     */
    List<DictionaryType> findAllDictionaryTypes();

}
