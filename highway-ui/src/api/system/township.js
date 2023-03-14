import request from '@/utils/request'

// 查询乡镇列表
export function listTownship(query) {
  return request({
    url: '/system/township/list',
    method: 'get',
    params: query
  })
}

// 查询乡镇详细
export function getTownship(townshipId) {
  return request({
    url: '/system/township/' + townshipId,
    method: 'get'
  })
}

// 新增乡镇
export function addTownship(data) {
  return request({
    url: '/system/township',
    method: 'post',
    data: data
  })
}

// 修改乡镇
export function updateTownship(data) {
  return request({
    url: '/system/township',
    method: 'put',
    data: data
  })
}

// 删除乡镇
export function delTownship(townshipId) {
  return request({
    url: '/system/township/' + townshipId,
    method: 'delete'
  })
}
