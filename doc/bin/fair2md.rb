#!/usr/bin/ruby
require "json"
require "erb"
require 'ostruct'
json_from_file = File.read("/Users/bouazzouni/Work/fairness/src/main/resources/config/common/questions.config.json")
hash = JSON.parse(json_from_file)
namespace = OpenStruct.new(hash: hash)

template = "
<% hash.each do |key , principle| %>
<% converts = {F:'Findable' , A:'Accessible' , I:'Interoperable' , R:'Reusable'} %>
 ## <%= converts[key.to_sym] %>
 <table>
    <tr>
        <th>Sub Principles </th>
        <th>Questions</th>
        <th>Questions credits</th>
        <th>Used properties</th>
        <th>State</th>
    </tr>
    <% principle.each  do |key , criterion| %><tr>
         <td rowspan=\"<%= criterion['questions'].size%>\">
              <%= key+': '+ criterion['label'] %>
              <ul>
                <li>Max Credits : <%= criterion['maxCredits'] %></li>
                <li>Portal max Credits : <%= criterion['portalMaxCredits'] %></li>
              </ul>
          </td>
         <td><%= criterion['questions'].values.first['question'] %></td>
         <td><%= criterion['questions'].values.first['points'] %></td>
      </tr>
      <% criterion['questions'].drop(1).to_h.each  do |key , question| %><tr>
            <td><%= question['question'] %></td>
            <td><%= question['points'] %></td>
            <td><%= question['properties']== nil ? 'none' : question['properties'] %></td>
            <td><%= question['properties'] == nil ? 'not implemented' : 'implemented'%></td>
         </tr><% end %>
    <% end %> </table>
 <% end %>
"
result = ERB.new(template).result(namespace.instance_eval { binding })
begin
  file = File.open(ARGV[0], "w")
  file.write(result)
rescue IOError => e
  #some error occur, dir not writable etc.
ensure
  file.close unless file.nil?
end

