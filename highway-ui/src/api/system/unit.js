import request from '@/utils/request'

// 查询养护单位管理列表
export function listUnit(query) {
  return request({
    url: '/system/unit/list',
    method: 'get',
    params: query
  })
}

// 查询养护单位管理详细
export function getUnit(muid) {
  return request({
    url: '/system/unit/' + muid,
    method: 'get'
  })
}

// 新增养护单位管理
export function addUnit(data) {
  return request({
    url: '/system/unit',
    method: 'post',
    data: data
  })
}

// 修改养护单位管理
export function updateUnit(data) {
  return request({
    url: '/system/unit',
    method: 'put',
    data: data
  })
}

// 删除养护单位管理
export function delUnit(muid) {
  return request({
    url: '/system/unit/' + muid,
    method: 'delete'
  })
}
