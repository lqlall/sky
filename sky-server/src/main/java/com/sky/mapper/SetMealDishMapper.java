package com.sky.mapper;

import com.sky.entity.SetmealDish;
import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * 根据菜品id查询套餐id
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    /**
     * 批量插入套餐菜品数据
     * @param dishes
     */
    void insertBatch(List<SetmealDish> dishes);

    /**
     * 根据套餐id查询套餐菜品数据
     * @param id
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> getDishesBySetMealId(Long id);

    /**
     * 删除套餐的同时删除套餐和菜品的关联数据
     * @param id
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void deleteBySetmealId(Long id);

    /**
     * 批量删除套餐关联菜品
     * @param ids
     */
    void deleteBySetmealIds(List<Long> ids);

    /**
     * 根据套餐id查询包含的菜品列表，并且每个菜品列出了当前套餐这个菜品的版本号
     * @param id
     * @return
     */
    @Select("select  sd.name , sd.copies, d.image, d.description from" +
            " setmeal_dish sd , dish d where sd.dish_id = d.id and sd.setmeal_id = #{id}")
    List<DishItemVO> getDishItemById(Long id);
}
