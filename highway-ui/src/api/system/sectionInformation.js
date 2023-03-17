import request from '@/utils/request'

// 查询路段信息列表
export function listSectionInformation(query) {
  return request({
    url: '/system/sectionInformation/list',
    method: 'get',
    params: query
  })
}

// 查询路段信息详细
export function getSectionInformation(roadsectionid) {
  return request({
    url: '/system/sectionInformation/' + roadsectionid,
    method: 'get'
  })
}

// 新增路段信息
export function addSectionInformation(data) {
  return request({
    url: '/system/sectionInformation',
    method: 'post',
    data: data
  })
}

// 修改路段信息
export function updateSectionInformation(data) {
  return request({
    url: '/system/sectionInformation',
    method: 'put',
    data: data
  })
}

// 删除路段信息
export function delSectionInformation(roadsectionid) {
  return request({
    url: '/system/sectionInformation/' + roadsectionid,
    method: 'delete'
  })
}
